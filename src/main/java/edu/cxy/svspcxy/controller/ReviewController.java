package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Report;
import edu.cxy.svspcxy.entity.Review;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.ReviewService;
import edu.cxy.svspcxy.util.JWTUtil;
import edu.cxy.svspcxy.util.OssUtil;
import edu.cxy.svspcxy.vo.ReviewAddVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 15:58
 */
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    private OssUtil ossUtil;

    @Resource
    private ReviewService reviewService;

    @CrossOrigin  // 允许跨域访问
    @PostMapping("/imageUpload")
    public ResponseResult imageUpload(MultipartFile file) throws IOException {
        String url = ossUtil.upOss(file);

        return new ResponseResult(HttpStatus.OK.value(), "success", url);
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody ReviewAddVo vo, HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        vo.setUid(uid);

        return new ResponseResult(HttpStatus.OK.value(), "success", reviewService.add(vo));
    }

    @GetMapping("/findByVid/{vid}")
    public ResponseResult findByVid(@PathVariable("vid") Integer vid){
        List<Review> reviewList = reviewService.findByVid(vid);
        return new ResponseResult(HttpStatus.OK.value(), "success", reviewList);
    }

    @GetMapping("/getAll")
    public ResponseResult getAll(){
        List<Review> reviewList = reviewService.getAll();
        return new ResponseResult(HttpStatus.OK.value(), "success", reviewList);
    }

    @GetMapping("/commit/{id}")
    /*审核状态*/
    public ResponseResult commitState(@PathVariable("id")Integer id, @QueryParam("reportState")String reviewState){
        reviewService.commitState(id,reviewState);
        return new ResponseResult(HttpStatus.OK.value(), "success", null);
    }
}
