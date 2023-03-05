package com.atguigu.educms.controller;
import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zhaolengquan
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/educms/bannerFront")
@CrossOrigin
@EnableSwagger2
public class BannerFrontController {

	@Autowired
	CrmBannerService bannerService;

	@ApiOperation("获取所有banner")
	@GetMapping("getAllBanner")
	@Cacheable(key = "'getAllBanner'",value ="banner")
	public R getAllBanner() {
		QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("sort");
		queryWrapper.last("limit 2");
		List<CrmBanner> list = bannerService.list(null);
		return R.ok().data("list", list);
	}
}

