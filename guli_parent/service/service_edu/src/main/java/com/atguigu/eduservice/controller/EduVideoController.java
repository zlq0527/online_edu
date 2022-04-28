package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.VideoForm.VideoForm;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
	@Autowired
	EduVideoService eduVideoService;

	@PostMapping("addVideo")
	public R addVideo(@RequestBody VideoForm videoForm) {
		EduVideo video = new EduVideo();
		BeanUtils.copyProperties(videoForm, video);
		boolean flag = eduVideoService.save(video);
		if (!flag) {
			R.error().message("添加小节失败");
		}
		return R.ok().message("添加成功");
	}

	@DeleteMapping("{courseId}")
	public R deleteVideo(@PathVariable String courseId) {
		boolean flag = eduVideoService.removeById(courseId);
		if (!flag) {
			return R.error().message("删除小节失败");
		}
		return R.ok().message("删除小节成功");
	}
}

