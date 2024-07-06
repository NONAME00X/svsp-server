package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Review;
import edu.cxy.svspcxy.vo.ReviewAddVo;

import java.util.List;

public interface ReviewService {
    boolean add(ReviewAddVo vo);

    List<Review> findByVid(Integer vid);
}
