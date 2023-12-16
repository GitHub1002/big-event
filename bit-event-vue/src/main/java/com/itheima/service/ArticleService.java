/**
 * @Classname ArticleService
 * @Date 2023/11/29 13:44
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {
    // 添加文章
    void add(Article article);

    // 分页查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 删除文章信息
    void delete(Integer id);

    // 查询文章详情
    Article detail(Integer id);

    // 更新文章信息
    void update(Article article);
}
