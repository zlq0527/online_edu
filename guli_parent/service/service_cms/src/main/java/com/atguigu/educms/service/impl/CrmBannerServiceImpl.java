package com.atguigu.educms.service.impl;

import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.mapper.CrmBannerMapper;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zhaolengquan
 * @since 2022-04-23
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public void pageQuery(Page<CrmBanner> pageBanner, CrmBanner bannerQuery) {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();

        if (ObjectUtils.isNotEmpty(bannerQuery)) {
            String name = bannerQuery.getTitle();
            if (!StringUtils.isEmpty(name)) {
                wrapper.like("title", name);
            }
            if (bannerQuery.getGmtCreate() != null) {
                String CreateTime = DateFormatUtils.format(bannerQuery.getGmtCreate(), "yyyy-MM-dd HH:mm:ss");
                wrapper.ge("gmt_create", CreateTime);
            }
            if (bannerQuery.getGmtModified() != null) {
                String EndTime = DateFormatUtils.format(bannerQuery.getGmtModified(), "yyyy-MM-dd HH:mm:ss");
                wrapper.le("gmt_modified", EndTime);
            }
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageBanner, wrapper);
    }
}
