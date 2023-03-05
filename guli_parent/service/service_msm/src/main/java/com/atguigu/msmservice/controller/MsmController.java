package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:10 2022/4/24
 * @ Description：
 */
@RestController
@RequestMapping("edumsm/msm")
@CrossOrigin
public class MsmController {
	@Autowired
	MsmService msmService;

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@GetMapping("send/{phone}")
	public R sendmsm(@PathVariable String phone) {
		String scode = redisTemplate.opsForValue().get(phone);
		if (!StringUtils.isEmpty(scode)) {
			return R.ok().message("验证码未过期");
		}
		String code = RandomUtil.getFourBitRandom();
		Map<String, Object> map = new HashMap<>();
		map.put("code",code);
		boolean flag = msmService.send(map, phone);
		if (flag) {
			redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
			return R.ok().data(map);
		} else {
			return R.error().message("短信发送失败!!!");
		}
	}
}
