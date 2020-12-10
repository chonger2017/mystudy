package com.dsh.excel.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-12-10_23:52
 */
@Component
public class MyTask {

    @Scheduled(cron = "0 30 10,14,16 * * ? ")
    public void sayHello() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(Thread.currentThread()+"hello world ===>" + sdf.format(date) + "====>"+date.getTime());
    }
}
