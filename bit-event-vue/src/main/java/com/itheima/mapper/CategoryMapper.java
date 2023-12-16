/**
 * @Classname CategoryMapper
 * @Date 2023/11/27 21:01
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 新增文章信息
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
            "values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    // 查询用户发布的所有文章信息
    @Select("select * from category where create_user = #{loginUserId}")
    List<Category> list(Integer loginUserId);

    // 查询文章的详细信息
    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now() where id = #{id}")
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
