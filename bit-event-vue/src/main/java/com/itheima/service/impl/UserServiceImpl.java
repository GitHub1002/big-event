/**
 * @Classname UserServiceImpl
 * @Date 2023/11/25 6:28
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String url) {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(url, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        HashMap<String, Object> map = ThreadLocalUtil.get();
        Integer loginUserId = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), loginUserId);
    }
}
