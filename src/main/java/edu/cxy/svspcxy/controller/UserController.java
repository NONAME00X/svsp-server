package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.vo.UserLoginVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口：登录、注册、修改头像、密码等
 * @author: Mr·Xiang
 * @create 2024-07-02 10:46
 */
// @RestController = @Controller + @ResponseBody 给当前类中所有的接口都自动加上@ResponseBody
@RestController  // 表示当前是一个controller类，可以用来接收请求，所有的接口返回值默认都是URL,页面
@RequestMapping("/user")
public class UserController {

    // 一般前端发送post请求时会将对象转换成JSON格式的字符串，然后进行提交
    // 后端接收到的其实是一个字符串，默认情况字符串转不成对象
    // @RequestBody：将字符串转换成对象，然后再赋值给形参

    @PostMapping("/login")
    //@ResponseBody  // 返回的json数据不是页面
    public String login(@RequestBody UserLoginVo vo){
        System.out.println(vo);

        return "person.html";
    }
}
