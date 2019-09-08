package com.dsh.daniel.xierqi;

import com.dsh.daniel.xierqi.config.MyPointConfig;
import com.dsh.daniel.xierqi.domain.SearchResult;
import com.dsh.daniel.xierqi.services.NearbyService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XierqiApplicationTests {

    @Autowired
    private NearbyService nearbyService;

    @Autowired
    private MyPointConfig myPointConfig;

    private String myName = "Daniel";//我的名字

    @Test
    public void contextLoads() {
    }

//    @Before
//    @Ignore
//    @Test
    public void initData() {
        int total = 10000;
        int inserted = 0;
        try {
            //建库，建表，建约束
            nearbyService.recreateIndex();
            //随机产生10w条数据
            nearbyService.addDataToIndex(myPointConfig.getLat(), myPointConfig.getLon(), total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n=========数据初始化工作完毕，共随机产生"+ inserted +"条数据 =========\n");
    }

    @Test
//	@Ignore
    public void searchNearby(){

        int size = 10, r = 100;

        System.out.println("开始获取距离" + myName + r + "m以内人");

        SearchResult result = nearbyService.search(myPointConfig.getLon(), myPointConfig.getLat(), r, size, null);

        System.out.println("共找到" + result.getTotal() + "个人,优先显示" + size + "人，查询耗时" + result.getUseTime() + "秒");
        for (Map<String,Object> people : result.getData()) {

            String nickName = people.get("nickName").toString();

            String location = people.get("location").toString();
            Object geo = people.get("geoDistance");

            System.out.println(nickName + "，" +
                    "微信号:" + people.get("wxNo") +
                    "，性别:" + people.get("sex") +
                    ",距离" + myName + geo + "米" +
                    "(坐标：" + location + ")");
        }

        System.out.println("以上" + size + "人显示在列表中......");
        System.out.println("各位老司机们尽管使出你们的撩妹技巧！！！");

    }
}
