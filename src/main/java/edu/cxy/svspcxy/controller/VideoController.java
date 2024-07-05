package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.request.ResPage;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.VideoService;
import edu.cxy.svspcxy.util.JWTUtil;
import edu.cxy.svspcxy.util.OssUtil;
import edu.cxy.svspcxy.vo.VideoAddVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Resource
    private OssUtil ossUtil;

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

    @PostMapping("/add")
    public ResponseResult add(VideoAddVo vo, HttpServletRequest request) throws IOException {

        log.debug(vo.toString());
        // 上传文件
        // 返回文件URL：返回的就是文件网络URL
        String coverUrl = ossUtil.upOss(vo.getCover());
        String videoUrl = ossUtil.upOss(vo.getVideo());

        // 将视频信息写入数据库
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        // 将数据封装
        Video video = new Video();
        video.setUid(uid);
        video.setVideo(videoUrl);
        video.setCover(coverUrl);
        video.setTitle(vo.getTitle());   // 标题标题如果包含敏感信息应该处理
        video.setCids(vo.getCids());
        videoService.addVideo(video);

        // 审核视频内容
        ossUtil.check(videoUrl, video.getId());

        return null;
    }
}
