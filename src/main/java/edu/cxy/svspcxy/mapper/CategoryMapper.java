package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> all();
}
