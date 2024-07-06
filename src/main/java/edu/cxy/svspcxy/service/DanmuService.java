package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Danmu;

import java.util.List;

public interface DanmuService {
    Danmu add(Danmu danmu);

    List<Danmu> findByVid(Integer vid);
}
