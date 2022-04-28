package com.atguigu.educenter.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zhaolengquan
 * @since 2022-04-24
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

	@Autowired
	UcenterMemberService ucenterMemberService;

	@PostMapping("login")
	public R login(@RequestBody UcenterMember user) {
		String token = ucenterMemberService.login(user);
		return R.ok().data("token", token);
	}

	@PostMapping("register")
	public R Register(@RequestBody RegisterVo registerVo) {
		ucenterMemberService.register(registerVo);
		return R.ok();
	}

	@GetMapping("getMemberInfo")
	public R getMemberinfo(HttpServletRequest request) {
		String memberid = JwtUtils.getMemberIdByJwtToken(request);//去header里取token
		UcenterMember ucenterMember = ucenterMemberService.getById(memberid);
		return R.ok().data("member", ucenterMember);
	}
}

