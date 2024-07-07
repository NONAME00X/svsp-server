package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.util.KeyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr·Xiang
 * @create 2024-07-07 11:14
 */
@RestController
@RequestMapping("/key")
public class KeyController {

    @GetMapping("/apply")
    public ResponseResult apply(){
        return new ResponseResult(HttpStatus.OK.value(), "success", KeyUtil.getKey());
    }
}
