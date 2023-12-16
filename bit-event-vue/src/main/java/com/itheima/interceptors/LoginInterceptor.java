/**
 * @Classname LoginInterceptor
 * @Date 2023/11/26 6:07
 * @Created by Mingkai Feng
 * @Description TODO
 */
package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            String redisToken = opsForValue.get(token);
            if (redisToken == null || !redisToken.equals(token)){
                // redis token已经失效
                throw new RuntimeException();
            }

            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将业务数据添加到 ThreadLocal 中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空 ThreadLocal 里的数据
        ThreadLocalUtil.remove();
    }
}
