package com.atguigu.eduvideo.utils;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 19:28 2022/4/28
 * @ Description：
 */
public class InitVodclient {
	private   final String keyid="LTAI5tKQx5bfJBDwi3wR3Sbr";
	private static  final String keysecret="5LJnXhcn9td45vhM16qiQCeBLpjufN";


	//填入AccessKey信息
	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
		String regionId = "cn-shanghai";  // 点播服务接入地域
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		return client;
	}


}


