package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> all();

    void commitState(@Param("id")Integer id, @Param("state")String categoryState);

    List<Category> allState();

    void add(String name);

    void edit(@Param("id")Integer id, @Param("name")String name);
}
