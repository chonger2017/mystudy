package com.dsh.daniel.xierqi.services;

import com.dsh.daniel.xierqi.domain.People;
import com.dsh.daniel.xierqi.domain.SearchResult;
import com.dsh.daniel.xierqi.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NearbyService {
    @Autowired
    private TransportClient client;

    private String indexName="nearby";//相当于数据库名称
    private String indexType = "wechat";//相当于数据表名称

    /**
     * 重建索引
     */
    public void recreateIndex() {
        try {
            //后台级的操作，关乎到删库跑路的危险
            if (!client.admin().indices().prepareExists(indexName).execute().actionGet().isExists()) {
                return;
            }
            //先清除原来已有的数据库
            client.admin().indices().prepareDelete(indexName).execute().actionGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createIndex();
    }

    public Integer addDataToIndex(double myLat, double myLon, int count) {
        List<XContentBuilder> contents = new ArrayList<>();

        //开启重复校验的缓存区
        RandomUtil.openCache();

        for(long i=0; i<count; i++){
            People people = randomPeople(myLat, myLon);
            contents.add(obj2Xcontent(people));
        }

        //清空重复校验的缓存区
        RandomUtil.clearCache();

        //把数据批量写入到数据库表中
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for (XContentBuilder content : contents) {

            //请求往数据库.数据库表插入数据，相当于insert
            IndexRequest request = client.prepareIndex(indexName,indexType).setSource(content).request();
            bulkRequest.add(request);
        }

        //批量执行
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            log.info("创建索引出错");
        }
        return bulkRequest.numberOfActions();

    }


    public void createIndex() {
        //表结构(建约束)
        XContentBuilder mapping = createMapping();

        //建库
        //建库建表建约束
        CreateIndexResponse createIndexResponse = client.admin().indices().prepareCreate(indexName).execute().actionGet();
        if(!createIndexResponse.isAcknowledged()){
            log.info("无法创建索引【"+indexName+"】");
        }

        //建表
        PutMappingRequest putMapping = Requests.putMappingRequest(indexName).type(indexType).source(mapping);
        AcknowledgedResponse response = client.admin().indices().putMapping(putMapping).actionGet();
    }

    /**
     * 创建mapping，相当于创建表结构
     * @return
     */
    private XContentBuilder createMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder().startObject()
                    // 索引库名（类似数据库中的表）
                    .startObject(indexType).startObject("properties")
                    //微信号、ID，唯一主键
                    .startObject("wxNo").field("type", "string").endObject()
                    //昵称
                    .startObject("nickName").field("type", "string").endObject()
                    //性别
                    .startObject("sex").field("type","string").endObject()
                    //位置、坐标
                    .startObject("location").field("type", "geo_point").endObject()
                    .endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }

    public static People randomPeople(double myLat, double myLon) {
        //随机生成微信号
        String wxNo = RandomUtil.randomWxNo();

        String sex = RandomUtil.randomSex();

        String nickName = RandomUtil.randomNickName(sex);

        double[] point = RandomUtil.randomPoint(myLat,myLon);

        return new People(point[0],point[1],wxNo,nickName,sex);
    }

    /**
     * 将Java对象转换为JSON字符串（所谓的全文检索，玩的就是字符串）
     * @author 咕泡学院-Tom老师
     */
    private String obj2Json(People people) {
        String jsonData = null;
        try {
            // 使用XContentBuilder创建json数据
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild.startObject()
                    .field("wxNo", people.getWxNo())
                    .field("nickName", people.getNickName())
                    .field("sex",people.getSex())
                    .startObject("location")
                    .field("lat",people.getLat())
                    .field("lon",people.getLon())
                    .endObject()
                    .endObject();
            jsonData = jsonBuild.toString();
            log.info(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static XContentBuilder obj2Xcontent(People people) {
        XContentBuilder jsonBuild = null;
        try {
            jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild.startObject().field("wxNo", people.getWxNo())
                    .field("nickName", people.getNickName())
                    .field("sex", people.getSex())
                    .startObject("location")
                        .field("lat",people.getLat())
                        .field("lon",people.getLon())
                    .endObject()
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonBuild;
    }

    /**
     * 检索附近的人
     * @param lat
     * @param lon
     * @param radius
     * @param size
     * @author 咕泡学院-Tom老师
     */
    public SearchResult search(double lat, double lon, int radius, int size, String sex){
        SearchResult result = new SearchResult();

        String unit = DistanceUnit.METERS.toString();//坐标范围计量单位

        //获取一个查询规则构造器

        //工具类，用来构造我们查询的条件
        //排序规则、聚合规则、数据关键字、分词等等
        //select * from 表名 where 字段名=xxx
        SearchRequestBuilder srb = client.prepareSearch(indexName).setTypes(indexType);

        //相当于MySql limit，主要是用分页
        srb.setFrom(0).setSize(size);//取出优先级最高的size条数据


        //Where条件
        //地理坐标，Range范围、圆周（方圆多少米）
        //new GeoDistanceRangeQueryBuilder("location")
        QueryBuilder qb = new GeoDistanceQueryBuilder("location")
                //原点、坐标，也就是tom老师的位置
                .point(lon, lat)
                //方圆多少米以内的数据
                .distance("1000"+unit, DistanceUnit.METERS)
                //设置计算规则，是平面还是立体 (方圆多少米)
                .geoDistance(GeoDistance.PLANE);
        //给SearchRequestBuilder添加一个过滤条件
        srb.setPostFilter(qb);

        //选择型、要么是、要么不是
        //sex 要么男、女
        BoolQueryBuilder bq = QueryBuilders.boolQuery();
        //性别只有两种情况，要么男，要么女，世界没有不男不女的人
        if(!(sex == null || "".equals(sex.trim()))){
            bq.must(QueryBuilders.matchQuery("sex", sex));
        }
        srb.setQuery(bq);

        //差值最小的排在最前面
        //location asc
        //设置排序规则
        GeoDistanceSortBuilder geoSort = SortBuilders.geoDistanceSort("location",lon,lat);
        geoSort.unit(DistanceUnit.METERS);
        geoSort.order(SortOrder.ASC);
        //添加到SearchRequestBuilder
        srb.addSort(geoSort);

        //开始执行查询
        SearchResponse response = srb.execute().actionGet();

        //高亮分词（自带分词，以提高我们搜索的精准度）
        SearchHits hits = response.getHits();
        SearchHit[] searchHists = hits.getHits();

        //搜索的耗时
        Float usetime = response.getTook().getMillis() / 1000f;


        result.setTotal(hits.getTotalHits());
        result.setUseTime(usetime);
        result.setDistance(DistanceUnit.METERS.toString());
        result.setData(new ArrayList<Map<String,Object>>());
        for (SearchHit hit : searchHists) {
            // 获取距离值，并保留两位小数点
            BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
            Map<String, Object> hitMap = hit.getSourceAsMap();
            // 在创建MAPPING的时候，属性名的不可为geoDistance。
            hitMap.put("geoDistance", geoDis.setScale(0, BigDecimal.ROUND_HALF_DOWN));
            result.getData().add(hitMap);
        }

        return result;
    }
}
