package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zhaolengquan
 * @since 2022-04-24
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Override
	public String login(UcenterMember user) {
		String mobile = user.getMobile();
		String password = user.getPassword();
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			throw new GuliException(20000, "登录失败");
		}
		String mobile1 = user.getMobile();
		QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("mobile", mobile1);
		UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
		if (ucenterMember == null) {
			throw new GuliException(20001, "登录失败");
		}
		if (!ucenterMember.getPassword().equals(MD5.encrypt(password))) {
			throw new GuliException(20001, "密码错误");
		}
		if (ucenterMember.getIsDisabled()) {
			throw new GuliException(20001, "登录失败");
		}
		String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
		return jwtToken;
	}

	@Override
	public void register(RegisterVo registerVo) {
		String code = registerVo.getCode();
		String mobile = registerVo.getMobile();
		String nickname = registerVo.getNickname();
		String password = registerVo.getPassword();
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
			throw new GuliException(20000, "注册失败!");
		}
		String msm_code = redisTemplate.opsForValue().get(mobile);
		if (!code.equals(msm_code)) {
			throw new GuliException(20000, "注册失败!");
		}
		QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("mobile", mobile);
		Integer count = baseMapper.selectCount(queryWrapper);
		if (count >= 1) {
			throw new GuliException(500, "this phone is used");
		}
		UcenterMember member = new UcenterMember();
		member.setMobile(mobile);
		member.setNickname(nickname);
		member.setPassword(MD5.encrypt(password));
		member.setIsDisabled(false);
		member.setAvatar("https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png");
		baseMapper.insert(member);
	}

	@Override
	public int getOpenidCount(String openid) {
		QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("openid", openid);
		Integer count = baseMapper.selectCount(queryWrapper);
		return count;
	}
}
