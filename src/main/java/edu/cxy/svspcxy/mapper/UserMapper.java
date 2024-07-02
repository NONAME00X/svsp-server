package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByAccount(String account);
}
