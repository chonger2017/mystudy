package com.dsh.mybatis.mybatisgenerator;

import com.dsh.mybatis.mybatisgenerator.service.impl.MemberManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisGeneratorApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisGeneratorApplicationTests {

    @Autowired
    private MemberManagerService memberManagerService;
    @Test
    public void contextLoads() {
        memberManagerService.add(null);
    }

    @Test
    public void remove() {
        try {
            memberManagerService.remove(123L);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

}
