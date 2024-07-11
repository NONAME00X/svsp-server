package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper {
    List<Collect> findByUidAndSearch(@Param("uid")int uid,@Param("search") String search);

    void deleteCollect(Integer id);
}
