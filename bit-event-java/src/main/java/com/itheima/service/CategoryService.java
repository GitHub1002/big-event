/**
 * @Classname CategoryService
 * @Date 2023/11/27 21:01
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增文章信息
    void add(Category category);

    //  查询用户所有文章信息
    List<Category> list();

    // 查询文章细节
    Category detail(Integer id);

    // 更新文章信息
    void update(Category category);

    void delete(Integer id);
}
