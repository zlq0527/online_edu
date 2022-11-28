package com.atguigu.eduorder.client;

import com.atguigu.orderVo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Component
@FeignClient("service-edu")
public interface EduClient {

    @PostMapping("/eduservice/cource/getCourseInfo/{id}")
    CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String courseId);

}
