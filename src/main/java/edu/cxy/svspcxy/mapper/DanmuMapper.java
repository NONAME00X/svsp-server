package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Danmu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DanmuMapper {
    boolean add(Danmu danmu);

    List<Danmu> findByVid(Integer vid);
}
