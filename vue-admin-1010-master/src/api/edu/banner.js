import request from '@/utils/request' 

export default{
    //1、幻灯片列表（多条件分页查询）
    //page：当前页，limit：每页记录数，teacherQuery：条件对象
    getBannerListPage(page,limit,bannerQuery){
        return request({
            url: `/educms/bannerAdmin/pageBanner/${page}/${limit}`,
            method: 'post',
            //teacherQuery条件对象，如果后端使用RequestBody获取数据
            //data表示把对象转换成json对象进行传递到接口里
            data: bannerQuery
          })
    },
    //根据id删除幻灯片
    removeById(id){
        return request({
            url: `/educms/bannerAdmin/deleteBannerById/${id}`,
            method: 'post',
        })
    },
    //新增幻灯片
    saveBanner(banner){
        return request({
            url: `/educms/bannerAdmin/addBanner`,
            method: `post`,
            data: banner
        })
    },
    //根据id查询幻灯片
    updateById(id){
        return request({
            url: `/educms/bannerAdmin/getBannerById/${id}`,
            method: `get`,
        })
    },
    //修改幻灯片信息
    updateBannerInfo(crmBanner){
        return request({
            url: `/educms/bannerAdmin/updateBanner`,
            method: `post`,
            data: crmBanner
        })
    },
}


