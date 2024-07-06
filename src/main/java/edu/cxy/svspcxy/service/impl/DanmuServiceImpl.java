package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Danmu;
import edu.cxy.svspcxy.entity.UserLevel;
import edu.cxy.svspcxy.mapper.DanmuMapper;
import edu.cxy.svspcxy.mapper.UserLevelMapper;
import edu.cxy.svspcxy.service.DanmuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 9:24
 */
@Service
public class DanmuServiceImpl implements DanmuService {
    @Resource
    private UserLevelMapper userLevelMapper;

    @Resource
    private DanmuMapper danmuMapper;

    @Override
    public Danmu add(Danmu danmu) {
        // 查询当前用户的等级信息
        UserLevel level = userLevelMapper.findByUid(danmu.getUid());
        // 封装到弹幕对象中
        danmu.setDuration(level.getDuration());
        danmu.setMode(level.getMode());
        danmu.setColor(level.getColor());

        // 向弹幕表中插入弹幕信息
        danmuMapper.add(danmu);

        // 返回结果
        return danmu;
    }

    @Override
    public List<Danmu> findByVid(Integer vid) {
        return danmuMapper.findByVid(vid);
    }
}
