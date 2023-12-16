/**
 * @Classname UserMapper
 * @Date 2023/11/25 6:32
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 通过用户名查找用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    // 添加用户
    @Insert("insert into user(username, password, create_time, update_time)" +
            "values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    // 更新用户基本信息
    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = now() where id = #{id}")
    void update(User user);

    // 更新用户头像
    @Update("update user set user_pic = #{url}, update_time = now() where id = #{id}")
    void updateAvatar(String url, Integer id);

    @Update("update user set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);


}
