package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.UserLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLevelMapper {
    UserLevel findByUid(int uid);
}
