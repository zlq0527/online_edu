package com.atguigu.servicebase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.atguigu"))
                .paths(PathSelectors.any())
                .build();

    }
//    @Bean
//    public Docket webApiConfig2(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("视频上传接口")
//                .apiInfo(webApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.atguigu"))
//                .paths(PathSelectors.any())
//                .build();
//
//    }
    //test

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("接口测试网站")
                .description("接口文档")
                .version("1.0")
                .contact(new Contact("YL", "http://localhost:8002/doc.html", "1123@qq.com"))
                .build();
    }
}
