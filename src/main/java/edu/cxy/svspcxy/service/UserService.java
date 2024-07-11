package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.User;
import edu.cxy.svspcxy.vo.UserLoginVo;

public interface UserService {
    User login(UserLoginVo vo);

    User getUserInfo(int uid);

    void changeAvatar(int uid, String avatarUrl);

    Boolean changePwd(int uid, String oldPassword, String newPassword);
}
