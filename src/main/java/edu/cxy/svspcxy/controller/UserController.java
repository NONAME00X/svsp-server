package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.User;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.UserService;
import edu.cxy.svspcxy.util.JWTUtil;
import edu.cxy.svspcxy.util.OssUtil;
import edu.cxy.svspcxy.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户接口：登录、注册、修改头像、密码等
 * @author: Mr·Xiang
 * @create 2024-07-02 10:46
 */
@Slf4j
// @RestController = @Controller + @ResponseBody 给当前类中所有的接口都自动加上@ResponseBody
@RestController  // 表示当前是一个controller类，可以用来接收请求，所有的接口返回值默认都是URL,页面
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private OssUtil ossUtil;

    // 一般前端发送post请求时会将对象转换成JSON格式的字符串，然后进行提交
    // 后端接收到的其实是一个字符串，默认情况字符串转不成对象
    // @RequestBody：将字符串转换成对象，然后再赋值给形参
    @PostMapping("/login")
    //@ResponseBody  // 返回的json数据不是页面
    public ResponseResult<User> login(@RequestBody UserLoginVo vo, HttpServletResponse response){
        System.out.println(vo);

        User user = userService.login(vo);

        // 颁发令牌：携带用户信息加密字符串
        String token = JWTUtil.generateToken(user.getId(), user.getAccount());
        // 通过响应头的方式将token返回给浏览器
        response.setHeader("Authorization", token);


        return new ResponseResult<>(HttpStatus.OK.value(), "登录成功", user);
    }

    @GetMapping("/get")
    public ResponseResult getUserInfo(HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        User currentUser = userService.getUserInfo(uid);
        return new ResponseResult(HttpStatus.OK.value(), "success", currentUser);
    }

    @PostMapping("/changeAvatar")
    public ResponseResult changeAvatar(@RequestParam("avatar")MultipartFile avatar, HttpServletRequest request) throws IOException {

        log.debug(avatar.toString());
        // 上传文件
        // 返回文件URL：返回的就是文件网络URL
        String avatarUrl = ossUtil.upOss(avatar);

        // 将头像信息更新到用户
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        userService.changeAvatar(uid,avatarUrl);

        return new ResponseResult(HttpStatus.OK.value(),"success", true);
    }
    @GetMapping ("/changePwd")
    public ResponseResult changePwd(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword, HttpServletRequest request) throws IOException {
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        log.debug("oldPwd={}-----newPwd={}",oldPassword,newPassword);
        Boolean success = userService.changePwd(uid,oldPassword,newPassword);
        return new ResponseResult(HttpStatus.OK.value(),"success", success);
    }
}
