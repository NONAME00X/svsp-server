package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.request.ResPage;

import java.util.List;

public interface VideoService {
    ResPage<List<Video>> findByUid(Integer uid, Integer page, Integer size);

    boolean addVideo(Video video);

    void addTaskId(String url, String taskId);

    List<Video> findByStateCommit();

    void updateState(Video video);
}
