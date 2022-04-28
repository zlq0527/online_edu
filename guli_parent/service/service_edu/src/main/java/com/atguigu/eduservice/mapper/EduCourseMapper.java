package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.FrontVo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 14:11 2022/4/18
 * @ Description：
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
	CoursePublishVo getPublishCourseInfo(String courseId);

	CourseWebVo getBaseCourseinfo(String courseId);
}
