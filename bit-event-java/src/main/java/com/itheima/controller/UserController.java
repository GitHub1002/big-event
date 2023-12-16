/**
 * @Classname UserController
 * @Date 2023/11/25 6:25
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    // 注入 redis
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查看用户名是否被占用
        User user = userService.findByUsername(username);
        if (null == user) {
            userService.register(username, password);
        }
        // 已被占用
        else{
            return Result.error("用户名已被占用");
        }
        return Result.success();
    }

    @PostMapping("/login")
    public Result login (@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户是否存在
        User user = userService.findByUsername(username);
        // 判断用户是否存在
        if (user == null) {
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        else {
            if (Md5Util.getMD5String(password).equals(user.getPassword())) {
                // 登录成功
                HashMap<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("username", user.getUsername());
                String token = JwtUtil.getToken(claims);

                // 将 token 存入 redis 中
                ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
                opsForValue.set(token, token, 1, TimeUnit.HOURS);

                return Result.success(token);
            }
        }
        return Result.error("密码错误");
    }

    @GetMapping("userInfo")
    public Result<User> userInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("update")
    public Result<User> update (@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar (@RequestParam String avatarUrl){
        System.out.println("Use updateAvatar method");
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd (@RequestBody HashMap<String, String> map, @RequestHeader("Authorization") String token){
        // 得到前端出入的数据
        String oldPwd = map.get("old_pwd");
        String newPwd = map.get("new_pwd");
        String rePwd = map.get("re_pwd");
        // 判断传入的参数是否合法
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("传入参数不全");
        }

        // 判断传入的旧密码是否正确
        HashMap<String, Object> userMap = ThreadLocalUtil.get();
        String username = (String) userMap.get("username");
        User loginUser = userService.findByUsername(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("密码错误");
        }
        // 判断新密码是否一直
        if (!rePwd.equals(newPwd)){
            return Result.error("两次输入的密码不一致");
        }

        // 修改密码
        userService.updatePwd(newPwd);
        // 更新 token
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.getOperations().delete(token);
        return Result.success();
    }
}
