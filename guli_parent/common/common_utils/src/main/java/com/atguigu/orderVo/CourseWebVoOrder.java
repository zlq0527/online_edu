package com.atguigu.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: guli_parent
 * @description:
 * @author: Zongyao Luo
 * @create: 2021-08-24 20:47
 **/
@Data
public class CourseWebVoOrder {

    private String id;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "销售数量")
    private Long buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
    @ApiModelProperty(value = "课程简介")
    private String description;
    @ApiModelProperty(value = "讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String intro;
    @ApiModelProperty(value = "讲师头像")
    private String avatar;
    @ApiModelProperty(value = "一级课程类别ID")
    private String subjectLevelOneId;
    @ApiModelProperty(value = "一级类别名称")
    private String subjectLevelOne;
    @ApiModelProperty(value = "二级课程类别ID")
    private String subjectLevelTwoId;
    @ApiModelProperty(value = "二级类别名称")
    private String subjectLevelTwo;

}
