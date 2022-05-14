package com.atguigu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 21:06 2022/5/13
 * @ Description：
 */
@SpringBootApplication
@EnableFeignClients
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
