package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //1.根据课程id和用户id创建订单，返回订单id
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {
        //创建订单，返回订单号n
        String orderNo = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo", orderNo);
    }

    /**
     * 根据订单ID获取订单信息
     */
    @GetMapping("getOrderInfo/{order_no}")
    public R getOrderInfo(@PathVariable String order_no) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", order_no);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order", order);
    }


    //根据用户id和课程id查询订单信息
    @GetMapping("isBuyCourse/{memberId}/{courseId}")
    public boolean isBuyCourse(@PathVariable String memberId, @PathVariable String courseId) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberId).eq("course_id", courseId).eq("status", 1));
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

}

