package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> findByUid(Integer uid);

    boolean addVideo(Video video);

    boolean addVideoCategory(Video video);
}
