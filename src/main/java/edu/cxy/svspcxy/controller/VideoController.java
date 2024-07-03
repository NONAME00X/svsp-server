package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.request.ResPage;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.VideoService;
import edu.cxy.svspcxy.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 10:12
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {
    @Resource
    private VideoService videoService;

    @GetMapping("/findByUid/{aaa}/{bbb}")
    public ResponseResult findByUid(
            @PathVariable("aaa") Integer page,
            @PathVariable("bbb") Integer size, HttpServletRequest request){

        // debug：最低级别日志信息，默认情况是不会记录、打印
        // info：记录关键信息的级别，级别高于debug，默认会打印出来
        log.debug("page = {}", page);
        log.debug("size = {}", size);
        log.debug(request.getHeader("Authorization"));
        int uid = JWTUtil.getuid(request.getHeader(("Authorization")));
        // 查询数据
        ResPage<List<Video>> resPage = videoService.findByUid(uid, page, size);
        // 返回结果
        return new ResponseResult(HttpStatus.OK.value(),"success", resPage);
    }
}
