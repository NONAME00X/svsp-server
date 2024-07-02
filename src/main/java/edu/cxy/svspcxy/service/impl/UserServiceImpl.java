package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.User;
import edu.cxy.svspcxy.exception.AccountOrPasswordErrorException;
import edu.cxy.svspcxy.mapper.UserMapper;
import edu.cxy.svspcxy.service.UserService;
import edu.cxy.svspcxy.util.MD5Util;
import edu.cxy.svspcxy.vo.UserLoginVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Mr·Xiang
 * @create 2024-07-02 14:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(UserLoginVo vo) {
        // 查询对象
        User user = userMapper.findByAccount(vo.getAccount());

        // 判断是否查询到对象信息  如果user为null说明账号不存在
        if (user == null || !user.getPassword().equals(MD5Util.encryptMD5(vo.getPassword()))){
            // 抛异常
            throw new AccountOrPasswordErrorException("账号或者密码有误!");
        }


        return null;
    }
}
