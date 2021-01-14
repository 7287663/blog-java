package com.ywxs.blog.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ywxs.blog.common.util.Validate;
import com.ywxs.blog.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    @Value("${aliOss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliOss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliOss.signature}")
    private String signature;
    @Value("${aliOss.template}")
    private String template;

    @Value("${aliOss.endpoint}")
    private String endpoint;
    @Value("${aliOss.bucketName}")
    private String bucketName;
    @Value("${aliOss.url}")
    private String fileUrl;

//    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> upload(List<MultipartFile> files) throws IOException {
        System.out.println(bucketName);
        System.out.println(endpoint);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        List<String> res = new ArrayList<>();
        files.forEach(c -> {
                    String fileName = c.getOriginalFilename();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    String nextId = IdUtil.createSnowflake(1, 1).nextIdStr();
                    fileName = "fjImg/" + nextId + "." + suffix;
                    try {
                        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(c.getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    res.add(bucketName + "." + endpoint + "/" + fileName);
                }
        );
        ossClient.shutdown();
        return res;
    }

    @Override
    public String uploadNginx(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        isValidate(originalFilename);
        String url = "/root/nginx/settle_admin/app/";
        String path = url + originalFilename;
        File file1 = new File(path);
        if (file1.exists()) {
            file1.delete();
        }
        InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        byte[] bytes = new byte[1024];
        while (true) {
            int res = inputStream.read(bytes);
            if (res == -1) {
                break;
            }
            fileOutputStream.write(bytes, 0, res);
        }
        inputStream.close();
        fileOutputStream.close();
        return fileUrl + originalFilename;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println(file);
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String nextId = IdUtil.createSnowflake(1, 1).nextIdStr();
        fileName = "images/" + nextId + "." + suffix;
        try {
            ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();
        return "http://" + bucketName + "." + endpoint + "/" + fileName;
    }

    private void isValidate(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Assert.isTrue("apk".equals(suffix), "请上传apk文件！");
    }

    @Override
    public void sendSms(String phone) {
        /*Assert.isTrue(Validate.checkMobileNumber(phone), "手机号格式有误！");
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
            Assert.isTrue(code1.equals("OK"), js.getStr("Message"));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void deleteImg(String img) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String s = "images/";
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, s+img);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
