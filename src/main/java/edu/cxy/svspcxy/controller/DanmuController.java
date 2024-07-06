package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Danmu;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.DanmuService;
import edu.cxy.svspcxy.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 9:07
 */
@Slf4j
@RestController
@RequestMapping("/danmu")
public class DanmuController {
    @Resource
    private DanmuService danmuService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Danmu danmu, HttpServletRequest request){
        int uid = JWTUtil.getuid(request.getHeader("Authorization"));
        log.debug("uid = {}", uid);
        log.debug(danmu.toString());
        danmu.setUid(uid);
        // 调用service并返回结果
        return new ResponseResult(HttpStatus.OK.value(), "success", danmuService.add(danmu));
    }
}
