package com.atguigu.eduservice.controller;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @ Author     ：赵棱泉.
 * @ Date       ：Created in 14:23 2022/3/9
 * @ Description：
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

	@Autowired
	EduCourseService eduCourseService;

	@GetMapping("getCourseInfo/{id}")
	public R findCorsebyId(@PathVariable String id) {
		CourseInfoVo eduCourse = eduCourseService.getCourseInfoByCourseId(id);
		return R.ok().data("courseInfoVo", eduCourse);
	}

	@ApiOperation("添加课程信息")
	@PostMapping("addCourseInfo")
	public R addCourseInfo(@RequestBody CourseInfoVo courseInfo) {
		System.out.println("我进来了 come in");
		String courseInfoid = eduCourseService.saveCourseInfo(courseInfo);
		return R.ok().data("courseId", courseInfoid);
	}

	@GetMapping("")
	public R getCourseList() {
		System.out.println("查询所有的!!!");
		List<EduCourse> list = eduCourseService.list(null);
		return R.ok().data("list", list);
	}

	@DeleteMapping("{id}")
	public R deleteCourse(@PathVariable String id) {
		eduCourseService.deleteCourse(id);
		return R.ok();
	}

	@GetMapping("getPublishCourseInfo/{id}")
	public R getPublishCourseInfo(@PathVariable String id) {
		CoursePublishVo publishCourse = eduCourseService.publishCourseInfo(id);
		return R.ok().data("publishCourse", publishCourse);
	}

	@PostMapping("publishCourse/{id}")
	public R publishCourse(@PathVariable String id) {
		EduCourse eduCourse = new EduCourse();
		eduCourse.setId(id);
		eduCourse.setStatus("Normal");
		eduCourseService.updateById(eduCourse);
		return R.ok();
	}
	@PutMapping("updateCourseInfo")
	public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
		eduCourseService.updateCourse(courseInfoVo);
		return R.ok();
	}
}
