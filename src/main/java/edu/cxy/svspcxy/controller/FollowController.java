package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Collect;
import edu.cxy.svspcxy.entity.Follow;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.FollowService;
import edu.cxy.svspcxy.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Resource
    private FollowService followService;

    @GetMapping("/findByUid")
    public ResponseResult findByUid(HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        List<Follow> followList = followService.findByUid(uid);
        return new ResponseResult(HttpStatus.OK.value(), "success", followList);
    }

    @GetMapping("/add")
    public ResponseResult addFollow(@QueryParam("uid") Integer uid,HttpServletRequest request){
        int fid = JWTUtil.getuid(request.getHeader("Authorization"));
        //是否关注成功(重复关注不成功)
        Boolean isFollowed = followService.addFollow(uid,fid);
        return new ResponseResult(HttpStatus.OK.value(), "success", isFollowed);

    }
    @GetMapping("/delete/{id}")
    public ResponseResult deleteFollow(@PathVariable("id")Integer id){
        followService.deleteFollow(id);
        return new ResponseResult(HttpStatus.OK.value(), "success", null);
    }

}
