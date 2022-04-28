package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 21:59 2022/4/24
 * @ Description：
 */
@Data
public class RegisterVo implements Serializable {
	@ApiModelProperty(value = "昵称")
	private String nickname;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "验证码")
	private String code;
}
