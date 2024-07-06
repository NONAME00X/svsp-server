package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Danmu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DanmuMapper {
    boolean add(Danmu danmu);
}
