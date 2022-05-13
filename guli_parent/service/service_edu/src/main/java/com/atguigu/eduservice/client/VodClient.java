package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:23 2022/5/13
 * @ Description：
 */
@Component
@FeignClient("service-vod")
public interface VodClient {

	@DeleteMapping("/eduvideo/video/removevideo/{id}")
	public R removevideo(@PathVariable("id") String id);
}
