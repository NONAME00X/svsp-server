package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Collect;
import edu.cxy.svspcxy.entity.Review;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.CollectService;
import edu.cxy.svspcxy.service.ReviewService;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;
    @GetMapping("/get")
    public ResponseResult findByUidAndSearch(@QueryParam("search")String search, HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        List<Collect> collectList = collectService.findByUidAndSearch(uid,search);
        return new ResponseResult(HttpStatus.OK.value(), "success", collectList);
    }
    @GetMapping("/delete/{id}")
    public ResponseResult deleteCollect(@PathVariable("id")Integer id){
        collectService.deleteCollect(id);
        return new ResponseResult(HttpStatus.OK.value(), "success", null);
    }
}
