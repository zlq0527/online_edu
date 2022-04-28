import request from '@/utils/request'

export default{

    //会员登录
    submitLogin(userInfo){
        return request({
            url: '/educenter/member/login',
            method: 'post',
            data: userInfo
        })
    },

    //根据token获取会员信息
    getMemberInfo(){
        return request({
            url: '/educenter/member/getMemberInfo',
            method: 'get',
        })
    }
}
