package com.atguigu.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:09 2022/4/24
 * @ Description：
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class MsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsmApplication.class, args);
	}
}
