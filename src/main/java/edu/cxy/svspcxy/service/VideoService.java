package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.request.ResPage;
import edu.cxy.svspcxy.vo.VideoTitleVo;

import java.util.List;

public interface VideoService {
    ResPage<List<Video>> findByUid(Integer uid, Integer page, Integer size,String search);

    boolean addVideo(Video video);

    void addTaskId(Integer id, String taskId);

    List<Video> findByStateCommit();

    void updateState(Video video);

    List<Video> findNew();

    Video findById(Integer id);

    boolean addPlayNums(Integer id);

    ResPage<List<Video>> findAll(Integer page, Integer size,String search,String state);

    boolean lock(Integer id);

    void editVideo(Integer id, String title);

    void deleteVideo(Integer id);

    List<VideoTitleVo> findAllTitle(int uid);

    void commitState(Integer id, Boolean videoPass);
}
