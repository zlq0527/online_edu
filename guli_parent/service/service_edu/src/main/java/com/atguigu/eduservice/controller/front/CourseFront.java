package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.FrontVo.CourseFrontVo;
import com.atguigu.eduservice.entity.FrontVo.CourseWebVo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 22:59 2022/4/23
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/courseFront")
public class CourseFront {

	@Autowired
	EduCourseService courseService;

	@Autowired
	EduChapterService chapterService;

	/**
	 * 	查询课程详情
	 */
	@PostMapping("getFrontCourse/{page}/{limit}")
	public R getFrontCourse(@PathVariable long page, @PathVariable long limit,
							@RequestBody(required = false) CourseFrontVo courseFrontVo) {
		Page<EduCourse> pagecourse = new Page<>(page, limit);
		Map<String, Object> map = courseService.getFrontCourseList(pagecourse,courseFrontVo);
		return R.ok().data(map);
	}

	@GetMapping("getFrontCourseInfo/{id}")
	public R getFrontCourseInfo(@PathVariable String id) {
		CourseWebVo courseWebVo=courseService.getBaseCourseinfo(id);
		System.out.println("coursewebvo----"+courseWebVo);
		List<ChapterVo> chapterList = chapterService.getChapterVideoByCourseId(id);
		return R.ok().data("courseWebVo", courseWebVo).data("chapterVoList",chapterList);
	}
}
