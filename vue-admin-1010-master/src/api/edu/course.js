// import request from '@/utils/request'
// export default {
//   // 1 添加课程信息
//   addCourseInfo(courseInfo) {
//     return request({
//       url: '/eduService/course/addCourseInfo',
//       method: 'post',
//       data: courseInfo
//     })
//   },
//   // 2 查询所有讲师
//   getListTeacher() {
//     return request({
//       url: `/eduService/teacher/findAll`,
//       method: 'get'
//     })
//   },
//
//   // 根据课程id查询课程基本信息
//   getCourseInfoId(id) {
//     return request({
//       url: `/eduService/course/getCourseInfo/` + id,
//       method: 'get'
//     })
//   },
//   // 修改课程信息
//   updateCourseInfo(courseInfo) {
//     return request({
//       url: `/eduService/course/updateCourseInfo`,
//       method: 'post',
//       data: courseInfo
//     })
//   },
//   // 课程确认信息显示
//   getPublihCourseInfo(id) {
//     return request({
//       url: `/eduService/course/getPublishCourseInfo/` + id,
//       method: 'get'
//     })
//   },
//   // 课程最终发布
//   publihCourse(id) {
//     return request({
//       url: `/eduService/course/publishCourse/` + id,
//       method: 'post'
//     })
//   },
//   // TODO 课程列表
//   // 课程最终发布
//   getPublishCourseList() {
//     return request({
//       url: `/eduService/course`,
//       method: 'get'
//     })
//   },
//
//   getCourseListPage(current, limit, courseQuery) {
//     return request({
//       url: `/eduService/course/pageCourseCondition/${current}/${limit}`,
//       // url: `/eduService/course`,
//       method: 'post',
//       // courseQuery条件对象，后端用requestbody获取数据
//       // data表示把对象转换成json数据传递到接口里面
//
//       data: courseQuery
//     })
//   },

//
// }

import request from '@/utils/request'
export default {
  // 1 添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: '/eduservice/course/addCourseInfo',
      method: 'post',
      data: courseInfo
    })
  },
  // 2 查询所有讲师
  getListTeacher() {
    return request({
      url: '/eduservice/teacher/findAll',
      method: 'get'
    })
  },

  // 3.根据
  getCourseInfo(courseId) {
    return request({
      url: `/eduservice/course/getCourseInfo/${courseId}`,
      method: 'get'
    })
  },

  // 4.修改课程信息
  updateCourseInfo(courseInfo) {
    return request({
      url: '/eduservice/course/updateCourseInfo',
      method: 'put',
      data: courseInfo
    })
  },

  // 5.根据id查询课程确认信息
  getPublishCourseInfo(id) {
    return request({
      url: '/eduservice/course/getPublishCourseInfo/' + id,
      method: 'get'
    })
  },

  // 6.课程最终发布
  publishCourse(id) {
    return request({
      url: '/eduservice/course/publishCourse/' + id,
      method: 'post'
    })
  },

  // 7.课程列表
  getPublishCourseList() {
    return request({
      url: '/eduservice/course',
      method: 'get'
    })
  },

  deleteCourse(id) {
    return request({
      url: `/eduservice/course/${id}`,
      method: 'delete'

    })
  }
}
