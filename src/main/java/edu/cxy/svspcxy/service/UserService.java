package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.User;
import edu.cxy.svspcxy.vo.UserLoginVo;

public interface UserService {
    User login(UserLoginVo vo);
}
