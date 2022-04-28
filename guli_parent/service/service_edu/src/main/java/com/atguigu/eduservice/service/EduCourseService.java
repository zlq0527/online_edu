package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.FrontVo.CourseFrontVo;
import com.atguigu.eduservice.entity.FrontVo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ Author     ：赵棱泉.
 * @ Date       ：Created in 14:24 2022/3/9
 * @ Description：
 */
public interface EduCourseService extends IService<EduCourse> {
	void deleteCourse(String courseId);

	String saveCourseInfo(CourseInfoVo course);

	CourseInfoVo getCourseInfoByCourseId(String id);

	CoursePublishVo publishCourseInfo(String courseid);

	void updateCourse(CourseInfoVo courseInfoVo);

	Map<String, Object> getFrontCourseList(Page<EduCourse> teacherPage, CourseFrontVo courseFrontVo);

	CourseWebVo getBaseCourseinfo(String id);

}
