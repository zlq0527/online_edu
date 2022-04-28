package com.atguigu.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 10:21 2022/4/23
 * @ Description：
 */
@Data
public class SubjectData {
	@ExcelProperty(index = 0)
	private String oneSubjectName;


	@ExcelProperty(index = 1)
	private String twoSubjectName;
}
