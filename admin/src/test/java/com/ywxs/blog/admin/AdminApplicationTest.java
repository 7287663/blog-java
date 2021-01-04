package com.ywxs.blog.admin;

import com.ywxs.blog.service.IBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminApplicationTest {


    @Autowired
    PasswordEncoder encoder;

    @Test
    public void test(){
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }
}