package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.atguigu.msmservice.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ Author     ：zhaolengquan.
 * @ Date       ：Created in 13:12 2022/4/24
 * @ Description：
 */
@Service
public class MsmServiceimpl implements MsmService {
	@Override
	public boolean send(Map<String, Object> map, String phone) {
		if (StringUtils.isBlank(phone)) {
			return false;
		}

		IClientProfile profile = DefaultProfile.getProfile("cn-beijing", "LTAI5tKQx5bfJBDwi3wR3Sbr",
				"5LJnXhcn9td45vhM16qiQCeBLpjufN");

		IAcsClient client = new DefaultAcsClient(profile);
		//组装请求对象
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setAction("SendSms");
		request.setVersion("2017-05-25");
//		request.setSysMethod(MethodType.POST);
//		request.setSysDomain("dysmsapi.aliyuncs.com");
//		request.setSysVersion("2017-05-25");
//		request.setSysAction("SendSms");


		//设置发送相关的参数
		request.putQueryParameter("PhoneNumbers", phone);        //手机号
		//必填:短信签名-可在短信控制台中找到
		request.putQueryParameter("SignName", "美美的梦");        //申请阿里云 签名名称
		//申请阿里云 模板code
		request.putQueryParameter("TemplateCode", "SMS_239313257");
		//验证码数据，转换json数据传递
		request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
		System.out.println(JSONObject.toJSONString(map));
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println("response.getData()"+response.getData());
			boolean success = response.getHttpResponse().isSuccess();
			return success;
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
	}
}
