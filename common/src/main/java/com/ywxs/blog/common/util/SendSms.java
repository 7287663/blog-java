package com.ywxs.blog.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

public class SendSms {
    @Value("${aliOss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliOss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliOss.signature}")
    private String signature;
    @Value("${aliOss.template}")
    private String template;


    private final RedisTemplate<String, String> redisTemplate;

    public SendSms(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void sendSms(String phone){
        Assert.isTrue(Validate.checkMobileNumber(phone), "手机号格式有误！");
        String code = Convert.toStr(RandomUtil.randomInt(100000, 999999));

        redisTemplate.opsForValue().set("code_" + phone, code, 5, TimeUnit.MINUTES);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signature);
        request.putQueryParameter("TemplateCode", template);
        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            JSONObject js = JSONUtil.parseObj(commonResponse.getData());
            String code1 = js.getStr("Code");
            Assert.isTrue(code1.equals("OK"),js.getStr("Message"));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
