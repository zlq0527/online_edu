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

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 18:38 2022/4/23
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("")
public class BannerAdminController {
    @Autowired
    CrmBannerService bannerService;

    @GetMapping("/pagebanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page,@PathVariable Long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        IPage<CrmBanner> rows = bannerService.page(pageBanner, null);
        return R.ok().data("count", rows.getTotal()).data("rows", rows.getRecords());
    }

    @PostMapping("/addbanner")
    public R addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

}
