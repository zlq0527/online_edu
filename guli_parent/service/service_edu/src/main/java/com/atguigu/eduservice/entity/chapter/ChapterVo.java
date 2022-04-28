package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 20:59 2022/4/19
 * @ Description：
 */
@Data
public class ChapterVo {
	private String id;
	private String title;
	private List<VideoVo> children = new ArrayList<>();
}
