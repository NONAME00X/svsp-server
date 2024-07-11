package edu.cxy.svspcxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.mapper.VideoMapper;
import edu.cxy.svspcxy.request.ResPage;
import edu.cxy.svspcxy.service.VideoService;
import edu.cxy.svspcxy.util.WebSocketUtil;
import edu.cxy.svspcxy.vo.VideoTitleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.websocket.Session;
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
    public ResPage<List<Video>> findByUid(Integer uid, Integer page, Integer size,String search) {
        // 设置分页信息
        PageHelper.startPage(page, size);
        // 执行sql
        List<Video> videoList = videoMapper.findByUid(uid,search);
        //给每个video插入likenums和collectnums
        for (Video video:videoList) {
            video.setLikenums(videoMapper.getLikeNumsById(video.getId()));
            video.setCollectnums(videoMapper.getCollectNumsById(video.getId()));
        }
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
        log.debug("video.id = {}", video.getId());
        // 插入视频信息
        videoMapper.addVideo(video);
        // 插入视频分类信息：需要刚才插入的视频的id
        log.debug("video.id = {}", video.getId());
        videoMapper.addVideoCategory(video);

        return true;
    }

    @Override
    public void addTaskId(Integer id, String taskId) {
        // 通过视频的URL设置任务id
        videoMapper.addTaskId(id, taskId);
    }

    @Override
    public List<Video> findByStateCommit() {
        return videoMapper.findByStateCommit();
    }

    @Override
    public void updateState(Video video) {
        videoMapper.updateState(video);
    }

    @Override
    public List<Video> findNew() {
        return videoMapper.findNew();
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.findById(id);
    }

    @Transactional
    @Override
    public boolean addPlayNums(Integer id) {
        return videoMapper.addPlayNums(id);
    }

    @Override
    public ResPage<List<Video>> findAll(Integer page, Integer size,String search,String state) {
        PageHelper.startPage(page, size);
        //
        List<Video> videoList = videoMapper.findAll(search,state);
        // 获取分页信息
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);
        // 封装分页信息返回给前端
        ResPage<List<Video>> resPage = new ResPage<>();
        resPage.setPage(page);  // 当前页
        resPage.setSize(size);  // 页大小
        resPage.setTotalPage(pageInfo.getPages());  // 总页数
        resPage.setTotal(pageInfo.getTotal());      // 总条数
        resPage.setData(videoList);

        //
        return resPage;
    }

    @Transactional
    @Override
    public boolean lock(Integer id) {
        videoMapper.lock(id);
        // 通知up主视频被锁定：websocket
        // 获取到用户的账号、视频的标题  消息就是字符串，想拼接啥都行
        // 用户账号
        String account = videoMapper.findAccountByVid(id);
        // 视频标题
        String title = videoMapper.findTitleById(id);
        // 拼接消息
        String message = account + "您的视频：《" + title + "》被管理员锁定";
        // 发消息
        Session session = WebSocketUtil.MESSAGEMAP.get(account);
        WebSocketUtil.sendMessage(session, message);

        return true;
    }

    @Transactional
    @Override
    public void editVideo(Integer id, String title) {
        videoMapper.editVideo(id,title);
    }

    @Transactional
    @Override
    public void deleteVideo(Integer id) {
        videoMapper.deleteVideo(id);
    }

    @Override
    public List<VideoTitleVo> findAllTitle(int uid) {
        List<VideoTitleVo> titles= videoMapper.findAllTitle(uid);
        return titles;
    }

    @Transactional
    @Override
    public void commitState(Integer id, Boolean videoPass) {
        String videoState="";
        if(videoPass)
            videoState="video_pass";
        else
            videoState="video_reject";
        videoMapper.commitState(id,videoState);
    }

}
