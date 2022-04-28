package com.atguigu.educenter.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.WXconstants;
import com.atguigu.educenter.Utils.HttpClientUtils;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 16:46 2022/4/26
 * @ Description：
 */

@CrossOrigin
@Controller
@RequestMapping("/educenter/wx")
public class WxapiController {

	@Autowired
	UcenterMemberService memberService;

	/**
	 * 1.生成微信扫描二维码
	 */
	@GetMapping("/login")
	public String getWxCode() {
		//固定地址，后面拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ ConstantWxUtils.WX_OPEN_APP_ID+"&response_type=code";
		// 微信开放平台授权baseUrl  %s相当于?代表占位符
		String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
				"?appid=%s" +
				"&redirect_uri=%s" +
				"&response_type=code" +
				"&scope=snsapi_login" +
				"&state=%s" +
				"#wechat_redirect";
		//对redirect_url进行URLEncoder编码
		String redirectUrl = WXconstants.WX_OPEN_REDIRECT_URL;
		try {
			redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
		} catch (Exception e) {
		}
		//设置%s里面值
		String url = String.format(
				baseUrl,
				WXconstants.WX_OPEN_APP_ID,
				redirectUrl,
				"zhaolengquan"
		);
		//重定向到请求微信地址里面
		return "redirect:" + url;
	}

	@GetMapping("callback")
	public String callback(String code, String state) {
		try {
			String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
					"?appid=%s" +
					"&secret=%s" +
					"&code=%s" +
					"&grant_type=authorization_code";
			//拼接三个参数 ：id  秘钥 和 code值
			String accessTokenUrl = String.format(
					baseAccessTokenUrl,
					WXconstants.WX_OPEN_APP_ID,
					WXconstants.WX_OPEN_APP_SECRET,
					code
			);
			String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
			Gson gson = new Gson();
			HashMap<String, String> mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
			String openid = ((String) mapAccessToken.get("openid"));
			String access_token = (String) mapAccessToken.get("access_token");
			int count = memberService.getOpenidCount(openid);
			QueryWrapper<UcenterMember> queryWrappern = new QueryWrapper<>();
			queryWrappern.eq("openid", openid);
			UcenterMember member = memberService.getOne(queryWrappern);
			if (count < 1) {
				String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
						"?access_token=%s" +
						"&openid=%s";
				//拼接两个参数
				String userInfoUrl = String.format(
						baseUserInfoUrl,
						access_token,
						openid
				);
				String userinfo = HttpClientUtils.get(userInfoUrl);
				HashMap UserinfoMap = gson.fromJson(userinfo, HashMap.class);
				String nickname = (String) UserinfoMap.get("nickname");
				String avatar = (String) UserinfoMap.get("headimgurl");
				member = new UcenterMember();
				member.setAvatar(avatar);
				member.setNickname(nickname);
				member.setOpenid(openid);
				memberService.save(member);
			}
			String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
			return "redirect:http://localhost:3000?token=" + jwtToken;

		} catch (Exception e) {
			throw new GuliException(20001, "登录失败");
		}
	}
}
