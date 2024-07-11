package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> all();

    void commitState(Integer id, String categoryState);

    List<Category> allState();

    void add(String name);

    void edit(Integer id, String name);
}
