import request from '@/utils/request' 

export default{

    //分页查询所有评论
    getCommentPage(page,limit,bannerQuery){
        return request({
            url: `/eduservice/edu-comment/getComment/${page}/${limit}`,
            method: 'post',
            //teacherQuery条件对象，如果后端使用RequestBody获取数据
            //data表示把对象转换成json对象进行传递到接口里
            data: bannerQuery
          })
    },
    //根据id删除评论
    removeById(id){
        return request({
            url: `/eduservice/edu-comment/removeById/${id}`,
            method: 'post',
        })
    },
}


