package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {
    List<Follow> findByUid(int uid);

    void addFollow(@Param("uid") Integer uid,@Param("fid") int fid);

    Integer isFollow(@Param("uid")Integer uid, @Param("fid")int fid);

    void deleteFollow(Integer id);
}
