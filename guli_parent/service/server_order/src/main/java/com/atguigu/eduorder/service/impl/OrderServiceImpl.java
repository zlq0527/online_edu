package com.atguigu.eduorder.service.impl;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.mapper.OrderMapper;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.atguigu.orderVo.CourseWebVoOrder;
import com.atguigu.orderVo.UcenterMemberOrder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient ;

    @Autowired
    private UcenterClient ucenterClient;


    @Autowired
    UcenterMemberService service;
    //1.生产订单
    @Override
    public String saveOrder(String courseId, String memberIdByJwtToken) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder ucenterMemberOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        //通过远程调用根据课程id获取课信息
        CourseWebVoOrder courseWebVoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建订单 设置数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebVoOrder.getTitle());
        order.setCourseCover(courseWebVoOrder.getCover());
        order.setTeacherName(courseWebVoOrder.getTeacherName());
        order.setTotalFee(courseWebVoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setEmail(ucenterMemberOrder.getEmail());
        order.setNickname(ucenterMemberOrder.getNickname());

        order.setStatus(0); //0 未支付 1 已支付
        order.setPayType(1); // 1 微信  2 支付宝
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
