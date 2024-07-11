package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.User;
import edu.cxy.svspcxy.exception.AccountOrPasswordErrorException;
import edu.cxy.svspcxy.mapper.UserMapper;
import edu.cxy.svspcxy.service.UserService;
import edu.cxy.svspcxy.util.MD5Util;
import edu.cxy.svspcxy.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author: Mr·Xiang
 * @create 2024-07-02 14:23
 */
@Slf4j
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
//            log.debug("MD5加密后的密码:{}",MD5Util.encryptMD5(vo.getPassword()));
            throw new AccountOrPasswordErrorException("账号或者密码有误!");
        }

        return user;
    }

    @Override
    public User getUserInfo(int uid) {
        User currentUser = userMapper.getUserInfo(uid);
        return currentUser;
    }

    @Transactional
    @Override
    public void changeAvatar(int uid, String avatarUrl) {
        userMapper.changeAvatar(uid,avatarUrl);
    }

    @Override
    public Boolean changePwd(int uid, String oldPassword, String newPassword) {
        // 查询对象
        User user = userMapper.getUserInfo(uid);
        // 判断是否查询到对象信息  如果user为null说明账号不存在
        if (user == null || !user.getPassword().equals(MD5Util.encryptMD5(oldPassword))) {
            return false;
        }

        log.debug("newPwd={}",MD5Util.encryptMD5(newPassword));
        userMapper.changePwd(uid,MD5Util.encryptMD5(newPassword));
        return true;
    }
}
