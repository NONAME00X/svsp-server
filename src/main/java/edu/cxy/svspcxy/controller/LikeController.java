package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.LikeService;
import edu.cxy.svspcxy.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 14:47
 */
@RestController
@RequestMapping("/like")
public class LikeController {
    @Resource
    private LikeService likeService;

    @GetMapping("/update/{isUp}/{vid}")
    public ResponseResult update(
            @PathVariable("isUp") Boolean isUp,
            @PathVariable("vid")Integer vid,
            HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));

        return new ResponseResult(
                HttpStatus.OK.value(),
                "success", likeService.update(isUp, uid, vid));
    }

    @GetMapping("/findByVid/{vid}")
    public ResponseResult findByVid(@PathVariable("vid") Integer vid,HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));

        return new ResponseResult(
                HttpStatus.OK.value(), "success", likeService.findByVid(vid, uid));
    }
}
