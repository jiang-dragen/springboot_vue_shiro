package com.jiang.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/*
@project name back
@author jiang
@time   2021-09-16-21:54

*/
@Component
@EnableSwagger2
public class SwaggerConfig {

 @Bean
 public Docket docket(){
  return new Docket(DocumentationType.SWAGGER_2)
          .groupName("姜")
          .apiInfo(apiInfo())
          .enable(true)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.jiang.back.controller"))
          .paths(PathSelectors.any())
          .build();
 }

 public ApiInfo apiInfo(){
  Contact DEFAULT_CONTACT = new Contact("姜关亮", "http://www.baidu.com", "1664857411@qq.com");
  return new ApiInfo("简易教务系统",
          "自学前后端分离项目练习",
          "1.0",
          "",
          DEFAULT_CONTACT,
          "Apache 2.0",
          "http://www.apache.org/licenses/LICENSE-2.0",
          new ArrayList<>());
 }
}
