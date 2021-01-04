package com.ywxs.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BaseService {

    /**
     * 上传图片可同时上传多个图片
     *
     * @param files
     */
    List<String> upload(List<MultipartFile> files) throws IOException;

    String uploadNginx(MultipartFile file) throws IOException;

    String upload(MultipartFile file) throws IOException;


    /**
     * 发送短信接口
     *
     * @param phone
     */
    void sendSms(String phone);


    void deleteImg(String img);
}

