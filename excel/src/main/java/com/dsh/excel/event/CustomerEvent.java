package com.dsh.excel.event;

import com.dsh.excel.model.UserBean;
import org.springframework.context.ApplicationEvent;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-12-22_23:19
 */
public class CustomerEvent extends ApplicationEvent {
    private UserBean userBean;

    public CustomerEvent(Object source, UserBean userBean) {
        super(source);
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return this.userBean;
    }
}
