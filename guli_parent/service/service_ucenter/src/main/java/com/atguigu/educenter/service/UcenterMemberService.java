package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zhaolengquan
 * @since 2022-04-24
 */
public interface UcenterMemberService extends IService<UcenterMember> {

	String login(UcenterMember user);

	void register(RegisterVo registerVo);

	int getOpenidCount(String openid);
}
