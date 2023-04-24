package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:08 2022/4/23
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexFront")
public class IndexFrontController {
	@Autowired
	EduCourseService courseService;
	@Autowired
	EduTeacherService teacherService;

	@Autowired
	EduCommentService commentService;


	@GetMapping("index")
	public R index() {
		QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("id");
		queryWrapper.last("limit 4");
		queryWrapper.eq("status", "Normal");
		List<EduCourse> courseList = courseService.list(queryWrapper);
		courseList.stream().forEach(n->{
			QueryWrapper<EduComment> queryWrapper2 = new QueryWrapper<>();
			queryWrapper2.eq("course_id", n.getId());
			int count = commentService.count(queryWrapper2);
			n.setViewCount(((long) count));
		});

		QueryWrapper<EduTeacher> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.orderByDesc("id");
		queryWrapper1.last("limit 4");
		List<EduTeacher> teacherList = teacherService.list(queryWrapper1);
		return R.ok().data("courseList",courseList).data("teacherList",teacherList);
	}
}
