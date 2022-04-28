package com.atguigu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 18:27 2022/4/23
 * @ Description：
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
//@MapperScan("com.atguigu.educms.mapper")
public class CmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}
}
