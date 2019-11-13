package cn.web.bike.webbike.service.impl;

import cn.web.bike.webbike.domain.User;
import cn.web.bike.webbike.service.UserService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean sendMsg(String nationCode, String phoneNum) {

        boolean flag = true;

        String code = (int)((Math.random()*9+1)*1000)+"";
        int appid = Integer.parseInt(stringRedisTemplate.opsForValue().get("appid"));
        String appkey = stringRedisTemplate.opsForValue().get("appkey");
        //生成一个随机四位数
//        try {
//            SmsSingleSender sender = new SmsSingleSender(appid, appkey);
//            SmsSingleSenderResult result = sender.send(0, nationCode, phoneNum, "", "", "");
            stringRedisTemplate.opsForValue().set(phoneNum,code,300, TimeUnit.SECONDS);
//        } catch (HTTPException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return flag;
    }

    @Override
    public boolean verify(String phoneNum, String verifyCode) {
        boolean flag = false;
        //调用redisTemplate，根据手机号key,查找对应的验证码
        String code = stringRedisTemplate.opsForValue().get(phoneNum);
        //将用户传入的验证码和实际的验证码对比
        if (code != null && code.equals(verifyCode)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void register(User user) {

        //调用mongodb的dao,存储用户的信息
        mongoTemplate.insert(user);
    }
}
