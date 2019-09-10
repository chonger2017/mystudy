package com.dsh.daniel.xierqi.controller;

import com.dsh.daniel.xierqi.annotation.PermissionCheck;
import com.dsh.daniel.xierqi.domain.Commodity;
import com.dsh.daniel.xierqi.domain.Response;
import com.dsh.daniel.xierqi.domain.User;
import com.dsh.daniel.xierqi.domain.VO.ResponseVO;
import com.dsh.daniel.xierqi.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/contextLoads")
    public String contextLoads() {
        System.out.println(commodityService.count());
        return "contextLoads";
    }

    @RequestMapping("/testInsert")
    public String testInsert() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009001");
        commodity.setName("原味切片面包（10片装）");
        commodity.setCategory("101");
        commodity.setPrice(880);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009002");
        commodity.setName("原味切片面包（6片装）");
        commodity.setCategory("101");
        commodity.setPrice(680);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009003");
        commodity.setName("原味切片面包（5片装）");
        commodity.setCategory("101");
        commodity.setPrice(600);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009005");
        commodity.setName("原味切片面包（4片装）");
        commodity.setCategory("101");
        commodity.setPrice(580);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009004");
        commodity.setName("元气吐司850g");
        commodity.setCategory("101");
        commodity.setPrice(120);
        commodity.setBrand("百草味");
        commodityService.save(commodity);
        return "testInsert";
    }

    @RequestMapping("/testDelete")
    public String testDelete() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009002");
        commodityService.delete(commodity);
        return "testDelete";
    }

    @RequestMapping("/testGetAll")
    public String testGetAll() {
        Iterable<Commodity> iterable = commodityService.getAll();
        iterable.forEach(e->System.out.println(e.toString()));
        return "testGetAll";
    }

    @RequestMapping("/testGetByName")
    public String testGetByName() {
        List<Commodity> list = commodityService.getByName("面包");
        System.out.println(list);
        return "testGetByName";
    }

    @RequestMapping("/testPage")
    public String testPage() {
        Page<Commodity> page = commodityService.pageQuery(0, 2, "切片");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
        return "testPage";
    }

    @RequestMapping("/test")
    @ResponseBody
    @PermissionCheck(resourceKey = "test")
    public ResponseVO<Void> test(@Validated @RequestBody User user) {
        System.out.println("test annotation");
        return Response.res_ok();
    }

    @RequestMapping("/testPermission")
    @ResponseBody
    @PermissionCheck(resourceKey = "test1")
    public ResponseVO<Void> testPermissionCheck(){
        System.out.println("权限成功");
        return Response.res_ok();
    }

}
