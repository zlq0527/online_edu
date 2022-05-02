package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

	@Autowired
	EduChapterService eduChapterService;

	@Autowired
	EduVideoService eduVideoService;

	@GetMapping("getChapterVideo/{courseId}")
	public R getChapterVideo(@PathVariable String courseId) {
		List<ChapterVo> eduChapter = eduChapterService.getChapterVideoByCourseId(courseId);
		return R.ok().data("allChapterVideo", eduChapter);
	}

	@PostMapping("addChapter")
	public R addChapter(@RequestBody EduChapter chapter) {
		boolean save = eduChapterService.save(chapter);
		if (!save) {
			throw new GuliException(20010, "添加章节失败!");
		}
		return R.ok();
	}

	@GetMapping("getChapterInfo/{chapterId}")
	public R getChapterInfo(@PathVariable String chapterId) {
		EduChapter chapter = eduChapterService.getById(chapterId);
		return R.ok().data("chapter", chapter);
	}

	@PostMapping("updateChapter")
	public R updateChapter(@RequestBody EduChapter chapter) {
		boolean save = eduChapterService.saveOrUpdate(chapter);
		if (!save) {
			throw new GuliException(400, "修改章节失败!");
		}
		return R.ok().message("修改章节成功!");
	}

	@DeleteMapping("{chapterId}")
	public R deleteChapter(@PathVariable String chapterId) {
		boolean flag = eduChapterService.removeById(chapterId);
		if (!flag) {
			return R.error().message("删除失败");
		}
		return R.ok();
	}
}
