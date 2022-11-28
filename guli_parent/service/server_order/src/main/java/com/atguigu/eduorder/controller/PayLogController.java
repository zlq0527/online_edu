package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/eduOrder/payLog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;
    //生成微信支付二维码的接口
    //参数是订单号
    @GetMapping("createWXPayCode/{orderNo}")
    public R createWXPayCode(@PathVariable String orderNo){
        //返回信息，包含二维码地址，还有其他信息
        Map map = payLogService.createWXPayCode(orderNo);
        System.out.println("返回信息，包含二维码地址，还有其他信息"+map);
        return R.ok().data(map);
    }


    //查询订单支付状态
    //参数:订单号，根据订单号查询支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //查询支付状态
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("根据订单号查询支付状态"+map);
        if (map == null) {
            //出错
            return R.error().message("支付出错了");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }



}

