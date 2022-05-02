package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-18
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
	@Autowired
	private EduSubjectService eduSubjectService;

	//添加课程分类
	//获取上传过来的文件，把文件内容读取出来
	@PostMapping("/addSubject")
	public R addSubject(MultipartFile file) {
		//获取上传的excel文件 MultipartFile
		eduSubjectService.saveSubject(file, eduSubjectService);
		//判断返回集合是否为空
		return R.ok();
	}

	@GetMapping("/getAllSubject")
	public R getallSubject() {
		List<OneSubject> list = eduSubjectService.getallonetwoSubject();
		return R.ok().data("list", list);
	}

	@GetMapping("/findAllSubject")
	public R findAllSubject() {
		List<OneSubject> list = eduSubjectService.getallonetwoSubject();
		return R.ok().data("items", list);
	}
}

