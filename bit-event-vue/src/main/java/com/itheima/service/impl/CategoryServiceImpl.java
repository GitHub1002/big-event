/**
 * @Classname CategoryServiceImpl
 * @Date 2023/11/27 21:01
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer loginUserId = (Integer) map.get("id");
        category.setCreateUser(loginUserId);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        Integer loginUserId = (Integer) map.get("id");
        List<Category> list = categoryMapper.list(loginUserId);
        return list;
    }

    @Override
    public Category detail(Integer id) {
        Category category = categoryMapper.detail(id);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
