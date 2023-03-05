package com.atguigu.educenter.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.entity.vo.UcenterMemberVo;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.orderVo.UcenterMemberOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zhaolengquan
 * @since 2022-04-24
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
@EnableSwagger2
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
		String memberid = JwtUtils.getMemberIdByJwtToken(request);
		UcenterMember ucenterMember = ucenterMemberService.getById(memberid);
		return R.ok().data("member", ucenterMember);
	}

	@GetMapping("getUserInfoOrder/{id}")
	public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id) {
		UcenterMember member = ucenterMemberService.getById(id);
		UcenterMemberOrder memberOrder = new UcenterMemberOrder();
		BeanUtils.copyProperties(member, memberOrder);
		return memberOrder;
	}
	//根据用户id查询用户信息
	@PostMapping("/getMemberInfoById/{memberId}")
	public UcenterMemberVo getMemberInfoById(@PathVariable String memberId){
		UcenterMember member = ucenterMemberService.getById(memberId);
		UcenterMemberVo memberVo = new UcenterMemberVo();
		BeanUtils.copyProperties(member,memberVo);
		return memberVo;
	}
}

