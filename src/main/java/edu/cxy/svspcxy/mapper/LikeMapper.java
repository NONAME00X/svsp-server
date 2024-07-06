package edu.cxy.svspcxy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    boolean addLike(@Param("uid") Integer uid,@Param("vid") Integer vid);

    boolean delLike(@Param("uid") Integer uid,@Param("vid") Integer vid);

    boolean findByVid(@Param("uid") Integer uid,@Param("vid") Integer vid);
}
