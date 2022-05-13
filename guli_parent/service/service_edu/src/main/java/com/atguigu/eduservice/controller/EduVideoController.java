package com.atguigu.eduservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.VideoForm.VideoForm;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.apache.commons.lang3.StringUtils;
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

	@Autowired
	VodClient vodClient;

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

	@DeleteMapping("{id}")
	public R deleteVideo(@PathVariable String id) {
		EduVideo eduVideo = eduVideoService.getById(id);
		String sourceId = eduVideo.getVideoSourceId();
		if (!StringUtils.isEmpty(sourceId)) {
			vodClient.removevideo(sourceId);
		}
		eduVideoService.removeById(id);
		return R.ok().message("删除小节成功");
	}
}

