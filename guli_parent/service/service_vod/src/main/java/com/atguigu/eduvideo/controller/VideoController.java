package com.atguigu.eduvideo.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduvideo.service.Videoservice;
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
}
