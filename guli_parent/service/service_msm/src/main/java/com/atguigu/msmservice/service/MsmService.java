package com.atguigu.msmservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:11 2022/4/24
 * @ Description：
 */
@Service
public interface MsmService {
	boolean send(Map<String, Object> map, String phone);
}
