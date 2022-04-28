package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 22:05 2022/4/23
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherFront")
public class TeacherFront {

	@Autowired
	EduTeacherService teacherService;
	@Autowired
	EduCourseService courseService;

	@PostMapping("getTeacherInfoList/{page}/{limit}")
	public R getTeacherInfoList(@PathVariable long page, @PathVariable long limit) {
		Page<EduTeacher> teacherPage = new Page<>(page, limit);
		Map<String, Object> teacherFrontList = teacherService.getTeacherFrontList(teacherPage);
		return R.ok().data(teacherFrontList);
	}

	@GetMapping("getTeacherFrontInfo/{id}")
	public R getTeacherFrontInfo(@PathVariable String id) {
		EduTeacher teacher = teacherService.getById(id);
		QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("teacher_id", id);
		List<EduCourse> courseList = courseService.list(queryWrapper);
		return R.ok().data("teacher", teacher).data("courseList", courseList);
	}

}
