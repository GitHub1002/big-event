/**
 * @Classname UserService
 * @Date 2023/11/25 6:28
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.service;

import com.itheima.pojo.User;

public interface UserService {

    User findByUsername(String username);

    void register(String username, String md5String);

    void update(User user);

    void updateAvatar(String url);

    void updatePwd(String newPwd);
}
