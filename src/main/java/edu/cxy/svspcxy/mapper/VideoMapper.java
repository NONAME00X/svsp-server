package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> findByUid(Integer uid);

    boolean addVideo(Video video);

    boolean addVideoCategory(Video video);

    // 将参数封装成了一个map进行传参
    void addTaskId(@Param("url") String url,@Param("taskId") String taskId);

    List<Video> findByStateCommit();

    void updateState(Video video);
}
