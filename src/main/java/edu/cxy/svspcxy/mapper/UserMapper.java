package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByAccount(String account);

    User getUserInfo(int uid);

    void changeAvatar(@Param("uid") int uid,@Param("avatarUrl") String avatarUrl);

    void changePwd(@Param("uid")int uid,@Param("newPwd") String newPassword);
}
