package edu.cxy.svspcxy.service;

public interface LikeService {
    boolean update(Boolean isUp, Integer uid, Integer vid);

    boolean findByVid(Integer vid, int uid);
}
