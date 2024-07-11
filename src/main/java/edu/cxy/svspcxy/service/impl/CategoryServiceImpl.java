package edu.cxy.svspcxy.service.impl;

import edu.cxy.svspcxy.entity.Category;
import edu.cxy.svspcxy.mapper.CategoryMapper;
import edu.cxy.svspcxy.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 16:02
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> all() {
        return categoryMapper.all();
    }

    @Override
    public void commitState(Integer id, String categoryState) {
        categoryMapper.commitState(id,categoryState);
    }

    @Override
    public List<Category> allState() {
        return categoryMapper.allState();
    }

    @Override
    public void add(String name) {
        categoryMapper.add(name);
    }

    @Override
    public void edit(Integer id, String name) {
        categoryMapper.edit(id,name);
    }
}
