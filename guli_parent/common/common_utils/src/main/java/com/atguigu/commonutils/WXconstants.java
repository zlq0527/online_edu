package com.atguigu.commonutils;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 16:45 2022/4/26
 * @ Description：
 */
@Component
@ConfigurationProperties("wx.open")
@Data
public class WXconstants implements InitializingBean {
	/**
	 * 微信开放平台appId
	 */
	private String appId;

	/**
	 * 微信开放平台appSecret
	 */
	private String appSecret;

	/**
	 * 微信开放平台 重定向url
	 */
	private String redirectUrl;

	public static String WX_OPEN_APP_ID;
	public static String WX_OPEN_APP_SECRET;
	public static String WX_OPEN_REDIRECT_URL;

	@Override
	public void afterPropertiesSet() throws Exception {
		WX_OPEN_APP_ID = appId;
		WX_OPEN_APP_SECRET = appSecret;
		WX_OPEN_REDIRECT_URL = redirectUrl;
	}
}
