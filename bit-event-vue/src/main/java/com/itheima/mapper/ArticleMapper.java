/**
 * @Classname ArticleMapper
 * @Date 2023/11/29 13:56
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 增加文章信息
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId},#{createUser}, now(), now())")
    void add(Article article);

    // 查询文章信息
    List<Article> list(Integer userId, Integer categoryId, String state);

    // 删除文章信息
    @Delete("delete from article where id = #{id}")
    void delete(Integer id);

    //  查询某个文章详情
    @Select("select * from article where id = #{id}")
    Article detail(Integer id);

    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = now() where id = #{id}")
    void update(Article article);
}
