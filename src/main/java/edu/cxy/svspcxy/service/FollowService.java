package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> findByUid(int uid);

    Boolean addFollow(Integer uid, int fid);


    void deleteFollow(Integer id);
}
