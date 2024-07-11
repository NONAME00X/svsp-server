package edu.cxy.svspcxy.controller;

import edu.cxy.svspcxy.entity.Follow;
import edu.cxy.svspcxy.entity.Report;
import edu.cxy.svspcxy.request.ResponseResult;
import edu.cxy.svspcxy.service.FollowService;
import edu.cxy.svspcxy.service.ReportService;
import edu.cxy.svspcxy.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
@RequestMapping("/report")
public class ReportController {

    @Resource
    private ReportService reportService;
    @GetMapping("/getAll")
    public ResponseResult getAll(){
        List<Report> reportList = reportService.getAll();
        return new ResponseResult(HttpStatus.OK.value(), "success", reportList);
    }
    @GetMapping("/commit/{id}")
    /*审核状态*/
    public ResponseResult commitState(@PathVariable("id")Integer id, @QueryParam("reportState")String reportState,@QueryParam("vid")Integer vid){
        log.debug("reportState:{}",reportState);
        reportService.commitState(id,reportState,vid);
        return new ResponseResult(HttpStatus.OK.value(), "success", null);
    }
}
