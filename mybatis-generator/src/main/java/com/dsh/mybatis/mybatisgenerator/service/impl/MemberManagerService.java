package com.dsh.mybatis.mybatisgenerator.service.impl;

import com.dsh.mybatis.mybatisgenerator.model.Member;
import com.dsh.mybatis.mybatisgenerator.service.IMemberManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberManagerService implements IMemberManagerService {

    private static Logger LOG = LoggerFactory.getLogger(MemberManagerService.class);

    /**
     *
     * @param member member
     * @return boolean
     */
    @Override
    public boolean add(Member member) {
        LOG.info("增加用户");
        return true;
    }

    @Override
    public boolean remove(long id) throws Exception {
        LOG.info("删除用户");
        throw new Exception("这是自己抛出来的异常");
    }

    @Override
    public boolean modify(Member member) {
        LOG.info("修改用户");
        return true;
    }

    @Override
    public boolean query(String loginName) {
        LOG.info("查询用户");
        return true;
    }
}
