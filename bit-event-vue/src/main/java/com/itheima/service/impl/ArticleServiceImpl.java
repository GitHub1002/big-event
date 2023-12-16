/**
 * @Classname ArticleServiceImpl
 * @Date 2023/11/29 13:44
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    // 添加文章信息
    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    // 查询文章信息列表
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建 PageBean 对象
        PageBean<Article> pd = new PageBean<>();
        // 2. 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // 3. 调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId, categoryId, state);
        Page<Article> p = (Page<Article>) as;

        // 4. 把数据填充到 PageBean 对象中
        pd.setTotal(p.getTotal());
        pd.setItems(p.getResult());
        return pd;
    }

    // 删除文章信息
    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    // 获取某个文章信息详情
    @Override
    public Article detail(Integer id) {
        Article article = articleMapper.detail(id);
        return article;
    }

    // 更新某个文章信息
    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }
}
