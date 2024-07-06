package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.mapper.LikeMapper;
import edu.cxy.svspcxy.service.LikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 14:50
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    private LikeMapper likeMapper;

    @Transactional
    @Override
    public boolean update(Boolean isUp, Integer uid, Integer vid) {
        if (isUp){
            // 点赞，向数据中条件一条数据
            likeMapper.addLike(uid, vid);
        }else {
            // 取消点赞，删除数据
            likeMapper.delLike(uid, vid);
        }
        return true;
    }
}
