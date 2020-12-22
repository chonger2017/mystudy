package com.dsh.excel.event;

import com.dsh.excel.model.UserBean;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-12-23_0:12
 */
@Component
public class CustomListener implements ApplicationListener<CustomerEvent> {
    @Override
    public void onApplicationEvent(CustomerEvent customerEvent) {
        UserBean userBean = customerEvent.getUserBean();
        System.out.println(userBean.toString());
    }
}
