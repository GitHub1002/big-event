/**
 * @Classname Category
 * @Date 2023/11/27 21:00
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 新增分类信息
    @PostMapping
    public Result add(@RequestBody @Validated(value = Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    // 查询用户发布的所有文章
    @GetMapping
    public Result<List<Category>> list(){
        return Result.success(categoryService.list());
    }

    // 更新分类
    @PutMapping
    public Result update(@RequestBody @Validated(value = Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    // 删除分类
    @DeleteMapping()
    public Result delete(@PathParam("categoryId") Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
