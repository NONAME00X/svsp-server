package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> findByUidAndSearch(int uid, String search);

    void deleteCollect(Integer id);
}
