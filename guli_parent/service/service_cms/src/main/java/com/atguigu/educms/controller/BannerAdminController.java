package com.atguigu.educms.controller;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ResultCode;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 18:38 2022/4/23
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("/educms/bannerAdmin")
public class BannerAdminController {
    @Autowired
    CrmBannerService bannerService;

    @PostMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit,
                        @RequestBody(required = false) CrmBanner bannerQuery) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        bannerService.pageQuery(pageBanner, bannerQuery);
        return R.ok().data("count", pageBanner.getTotal()).data("rows", pageBanner.getRecords());
    }

    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

    @GetMapping("{id}")
    public void getBannerById(@PathVariable String id) {
        bannerService.getOne(new QueryWrapper<CrmBanner>().eq("id", id));
    }

}
