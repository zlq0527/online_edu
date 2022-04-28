package com.atguigu.eduvideo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:17 2022/4/27
 * @ Description：
 */
@Service
public interface Videoservice {

	String uploadvideoByid(MultipartFile file) throws IOException;
}
