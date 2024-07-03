package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 15:53
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseResult all(){
        return new ResponseResult(HttpStatus.OK.value(),"success", categoryService.all());
    }
}
