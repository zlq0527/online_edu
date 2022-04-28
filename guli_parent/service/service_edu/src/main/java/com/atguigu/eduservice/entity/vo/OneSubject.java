package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:30 2022/4/18
 * @ Description：
 */
@Data
public class OneSubject {
	private String id;
	private String title;

	//一个一级分类有多个二级分类
	private List<TwoSubject> children = new ArrayList<>();
}
