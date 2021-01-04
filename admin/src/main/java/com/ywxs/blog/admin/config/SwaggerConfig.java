package com.ywxs.blog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean //配置docket以配置Swagger具体参数
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev","test"); //可变长参数
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //todo 修改
                .apis(RequestHandlerSelectors.basePackage("com.ywxs.blog.admin.controller"))
                .build()
                ;
    }

    //配置文档信息
    private ApiInfo apiInfo() {

        Contact contact = new Contact("", "", "");
        return new ApiInfo(
                //todo 修改
                "博客系统api文档", // 标题
                "", // 描述
                "v1.0", // 版本
                "", // 组织链接
                contact, // 联系人信息
                "", // 许可
                "", // 许可连接
                new ArrayList<>()// 扩展
        );
    }


}
