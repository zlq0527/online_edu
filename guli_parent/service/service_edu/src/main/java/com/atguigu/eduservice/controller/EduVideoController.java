package com.atguigu.eduservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.VideoForm.VideoForm;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.eduservice.utils.Alikey;
import com.atguigu.eduservice.utils.InitVodclient;
import com.atguigu.servicebase.exceptionhandler.GuliException;
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

	@DeleteMapping("{id}")
	public R deleteVideo(@PathVariable String id) {
		EduVideo eduVideo = eduVideoService.getById(id);
		System.out.println("eduvideo--------" + eduVideo);
		try {
			DefaultAcsClient client = InitVodclient.initVodClient(Alikey.id, Alikey.secret);
			DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
			deleteVideoRequest.setVideoIds(eduVideo.getVideoSourceId());
			client.getAcsResponse(deleteVideoRequest);
			eduVideoService.removeById(id);
		} catch (ClientException e) {
			e.printStackTrace();
			throw new GuliException(20001, "删除小节失败");
		}
		return R.ok().message("删除小节成功");
	}
}

