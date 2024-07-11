package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Review;
import edu.cxy.svspcxy.vo.ReviewAddVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    boolean add(ReviewAddVo vo);

    List<Review> findByVid(Integer vid);

    List<Review> getAll();

    void commitState(@Param("id")Integer id, @Param("state")String reviewState);
}
