package edu.cxy.svspcxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.mapper.VideoMapper;
import edu.cxy.svspcxy.request.ResPage;
import edu.cxy.svspcxy.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 11:21
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoMapper videoMapper;

    @Override
    public ResPage<List<Video>> findByUid(Integer uid, Integer page, Integer size) {
        // 设置分页信息
        PageHelper.startPage(page, size);
        // 执行sql
        List<Video> videoList = videoMapper.findByUid(uid);
        // 获取分页信息
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        //
        log.debug(pageInfo.toString());
        // 封装分页信息返回给前端
        ResPage<List<Video>> resPage = new ResPage<>();
        resPage.setPage(page);  // 当前页
        resPage.setSize(size);  // 页大小
        resPage.setTotalPage(pageInfo.getPages());  // 总页数
        resPage.setTotal(pageInfo.getTotal());      // 总条数
        resPage.setData(videoList);

        // 返回数据
        return resPage;
    }

    @Transactional  // 给当前方法添加事务管理的功能
    @Override
    public boolean addVideo(Video video) {
        videoMapper.addVideo(video);

        return false;
    }
}
