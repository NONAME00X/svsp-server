package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.vo.VideoTitleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> findByUid(@Param("uid") Integer uid,@Param("search") String search);

    boolean addVideo(Video video);

    boolean addVideoCategory(Video video);

    // 将参数封装成了一个map进行传参
    void addTaskId(@Param("id") Integer id,@Param("taskId") String taskId);

    List<Video> findByStateCommit();

    void updateState(Video video);

    List<Video> findNew();

    Video findById(Integer id);

    boolean addPlayNums(Integer id);

    List<Video> findAll(@Param("search")String search, @Param("state")String state);

    boolean lock(Integer id);

    String findAccountByVid(Integer id);

    String findTitleById(Integer id);

    //根据id获取点赞数和收藏数
    Integer getLikeNumsById(Integer id);
    Integer getCollectNumsById(Integer id);

    void editVideo(@Param("id") Integer id, @Param("title") String title);

    void deleteVideo(Integer id);

    List<VideoTitleVo> findAllTitle(int uid);

    void commitState(@Param("id") Integer id,@Param("state") String videoState);
}
