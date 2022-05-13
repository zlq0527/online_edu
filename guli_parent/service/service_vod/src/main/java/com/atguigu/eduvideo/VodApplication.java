package com.atguigu.eduvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 12:58 2022/4/27
 * @ Description：
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
@EnableDiscoveryClient
public class VodApplication {
	public static void main(String[] args) {
		SpringApplication.run(VodApplication.class, args);
	}

}
