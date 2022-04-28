package com.atguigu.eduvideo.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.eduvideo.service.Videoservice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:19 2022/4/27
 * @ Description：
 */
@Service
//"LTAI5tKQx5bfJBDwi3wR3Sbr"
// "5LJnXhcn9td45vhM16qiQCeBLpjufN";
public class Videoserviceimpl implements Videoservice {
	@Override
	public String uploadvideoByid(MultipartFile file) {

		//上传视频原始名称
		String originalFilename = file.getOriginalFilename();
		//上传之后显示的名称
		String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

		String videoId =null;

		//获取文件的输入流
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UploadStreamRequest request = new UploadStreamRequest("LTAI5tKQx5bfJBDwi3wR3Sbr", "5LJnXhcn9td45vhM16qiQCeBLpjufN",
				title, originalFilename, inputStream);
		UploadVideoImpl uploadVideo = new UploadVideoImpl();
		UploadStreamResponse response = uploadVideo.uploadStream(request);
		videoId = response.getVideoId();

		return videoId;

//		String title = "";
//		String fileName = file.getOriginalFilename();
//		InputStream inputStream = null;
//		try {
//			inputStream = file.getInputStream();
//			UploadStreamRequest request = new UploadStreamRequest("LTAI5tKQx5bfJBDwi3wR3Sbr", "5LJnXhcn9td45vhM16qiQCeBLpjufN",
//					title, fileName, inputStream);
//			UploadVideoImpl uploader = new UploadVideoImpl();
//			UploadStreamResponse response = uploader.uploadStream(request);
//			System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
//			if (response.isSuccess()) {
//				System.out.print("VideoId=" + response.getVideoId() + "\n");
//			} else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
//				System.out.print("VideoId=" + response.getVideoId() + "\n");
//				System.out.print("ErrorCode=" + response.getCode() + "\n");
//				System.out.print("ErrorMessage=" + response.getMessage() + "\n");
//			}
//			return response.getVideoId();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
	}
}
