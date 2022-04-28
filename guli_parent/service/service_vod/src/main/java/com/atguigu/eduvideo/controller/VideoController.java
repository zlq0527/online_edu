package com.atguigu.eduvideo.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.eduvideo.service.Videoservice;
import com.atguigu.eduvideo.utils.Alikey;
import com.atguigu.eduvideo.utils.InitVodclient;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.github.xiaoymin.swaggerbootstrapui.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 12:56 2022/4/27
 * @ Description：
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvideo/video")
public class VideoController {

	@Autowired
	Videoservice videoservice;

	@PostMapping("uploadvideo")
	public R uoloadvideo(@RequestPart("file") MultipartFile file) throws IOException {
		String videoId = videoservice.uploadvideoByid(file);
		return R.ok().data("videoId", videoId);
	}

	@GetMapping("getPlayAuth/{videoId}")
	public R getPlayAuth(@PathVariable String videoId) {
		try {
			DefaultAcsClient client = InitVodclient.initVodClient(Alikey.id, Alikey.secret);
			GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
			request.setVideoId(videoId);
			GetVideoPlayAuthResponse response = client.getAcsResponse(request);
			String Auth = response.getPlayAuth();
			return R.ok().data("playAuth",Auth);
		} catch (ClientException e) {
			e.printStackTrace();
			throw new GuliException(20001, "获取播放凭证失败");
		}
	}

	@DeleteMapping("removevideo/{id}")
	public R removevideo(@PathVariable String id){
		DefaultAcsClient client = null;
		try {
			client = InitVodclient.initVodClient(Alikey.id, Alikey.secret);
			DeleteVideoRequest request = new DeleteVideoRequest();
			request.setVideoIds(id);
			client.getAcsResponse(request);
			return R.ok();
		} catch (ClientException e) {
			e.printStackTrace();
			throw new GuliException(20001, "视频删除失败");
		}
	}
}
