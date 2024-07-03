package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.request.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 10:12
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/findByUid/{aaa}/{bbb}")
    public ResponseResult findByUid(@PathVariable("aaa") Integer page,@PathVariable("bbb") Integer size){
        // debug：最低级别日志信息，默认情况是不会记录、打印
        // info：记录关键信息的级别，级别高于debug，默认会打印出来
        log.debug("page = {}", page);
        log.debug("size = {}", size);
        return null;
    }
}
