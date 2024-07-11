package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Report;
import edu.cxy.svspcxy.mapper.ReportMapper;
import edu.cxy.svspcxy.mapper.VideoMapper;
import edu.cxy.svspcxy.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Resource
    private VideoMapper videoMapper;
    @Override
    public List<Report> getAll() {
        List<Report> reportList = reportMapper.getAll();
        return reportList;
    }

    @Override
    public void commitState(Integer id, String state, Integer vid) {

        reportMapper.commitState(id,state);
        //锁定视频
        if(state.equals("report_pass")){
            log.debug("------------------锁定视频:{}----------------------",vid);
            videoMapper.lock(vid);
        }
    }
}
