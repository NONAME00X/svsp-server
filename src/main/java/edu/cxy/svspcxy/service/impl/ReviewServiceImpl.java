package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Review;
import edu.cxy.svspcxy.mapper.ReviewMapper;
import edu.cxy.svspcxy.service.ReviewService;
import edu.cxy.svspcxy.vo.ReviewAddVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 16:42
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;

    @Transactional
    @Override
    public boolean add(ReviewAddVo vo) {
        return reviewMapper.add(vo);
    }

    @Override
    public List<Review> findByVid(Integer vid) {
        return reviewMapper.findByVid(vid);
    }
}
