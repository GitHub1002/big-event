/**
 * @Classname ArticleController
 * @Date 2023/11/26 6:05
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.Category;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 查询所有文章信息
    @GetMapping("list")
    public Result<String> list (){
        return  Result.success("所有文章信息");
    }

    // 添加文章信息
    @PostMapping
    public Result article(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    // 分页查询文章信息
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pd = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pd);
    }

    // 查询某个文章的详细信息
    @GetMapping("detail")
    public Result<Article> detail(@PathParam("articleId") Integer articleId){
        System.out.println("use controller detail method to select id= "+ articleId );
        Article article = articleService.detail(articleId);
        return Result.success(article);
    }

    // 删除某个文章信息
    @DeleteMapping()
    public Result  delete(@PathParam(value = "articleId") Integer articleId){
        articleService.delete(articleId);
        return Result.success();
    }

    // 更新文章信息
    @PutMapping()
    public Result<Article> update(@RequestBody @Validated Article article){
        System.out.println("using Controller update method to update article");
        System.out.println("article.id is " + article.getId());
        System.out.println("article.content is " + article.getContent());
        System.out.println("article.coverImg is " + article.getCoverImg());
        System.out.println("article.title is " + article.getTitle());
        System.out.println("article.categoryId is " + article.getCategoryId());
        articleService.update(article);
        return Result.success();
    }
}
