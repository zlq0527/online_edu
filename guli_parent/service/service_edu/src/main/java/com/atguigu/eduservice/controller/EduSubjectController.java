package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

	@PostMapping("/addSubject")
	public R addSubject(MultipartFile file) {
		if (file.isEmpty()) {
			return R.error().message("文件不能为空！");
		}
		eduSubjectService.saveSubject(file, eduSubjectService);
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

