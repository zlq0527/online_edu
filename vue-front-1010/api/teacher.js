import request from '@/utils/request'

export default{

  //分页讲师查询的方法
  getTeacherList(page,size){
      return request({
          url: `/eduservice/teacherFront/getTeacherInfoList/${page}/${size}`,
          method: 'post'
      })
  },


  //讲师详情查询
  getTeacherInfo(teacherId){
      return request(
          {
            url: `/eduservice/teacherFront/getTeacherFrontInfo/${teacherId}`,
            method: 'get'
          }
      )
  }
}
