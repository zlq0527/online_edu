package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
public class EduLoginController {

    /**
     * 后台Login方法
     * @return
     */
    @PostMapping("login")
    public R login(){
        System.out.println("我是login方法");
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
