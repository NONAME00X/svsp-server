package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.vo.ReviewAddVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    boolean add(ReviewAddVo vo);
}
