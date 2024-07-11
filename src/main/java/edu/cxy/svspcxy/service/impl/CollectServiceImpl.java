package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Collect;
import edu.cxy.svspcxy.mapper.CollectMapper;
import edu.cxy.svspcxy.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService    {
    @Resource
    private CollectMapper collectMapper;
    @Override
    public List<Collect> findByUidAndSearch(int uid, String search) {
        List<Collect> collectList =collectMapper.findByUidAndSearch(uid,search);
            return collectList;
    }

    @Transactional
    @Override
    public void deleteCollect(Integer id) {
        collectMapper.deleteCollect(id);
    }
}
