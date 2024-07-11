package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

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

    @GetMapping("/allState")
    public ResponseResult allState(){
        return new ResponseResult(HttpStatus.OK.value(),"success", categoryService.allState());
    }
    @GetMapping("/commit/{id}")
    /*审核状态*/
    public ResponseResult commitState(@PathVariable("id")Integer id, @QueryParam("categoryState")String categoryState){
        categoryService.commitState(id,categoryState);
        return new ResponseResult(HttpStatus.OK.value(), "success", null);
    }
    @GetMapping("/add")
    public ResponseResult add(@QueryParam("name")String name){
        categoryService.add(name);
        return new ResponseResult(HttpStatus.OK.value(),"success", null);
    }

    @GetMapping("/edit/{id}")
    public ResponseResult edit(@PathVariable("id")Integer id,@QueryParam("name")String name){
        categoryService.edit(id,name);
        return new ResponseResult(HttpStatus.OK.value(),"success", null);
    }
}
