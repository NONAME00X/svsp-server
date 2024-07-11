package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Follow;
import edu.cxy.svspcxy.mapper.FollowMapper;
import edu.cxy.svspcxy.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowMapper followMapper;

    @Override
    public List<Follow> findByUid(int uid) {
        List<Follow> followList = followMapper.findByUid(uid);
        return followList;
    }

    @Transactional
    @Override
    public Boolean addFollow(Integer uid, int fid) {
        //判断是否已经关注，已经关注则无法关注
        Integer followed = followMapper.isFollow(uid,fid);
        log.debug("followedNum:{}",followed);
        if (followed>0)
            return false;
        followMapper.addFollow(uid,fid);
        return true;
    }

    @Override
    public void deleteFollow(Integer id) {
        followMapper.deleteFollow(id);
    }
}
