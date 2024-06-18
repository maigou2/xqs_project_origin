package com.xqs.userservice.interceptor;


import com.xqs.commoncore.constens.Constant;
import com.xqs.commoncore.exception.MyException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//        String token = request.getHeader("token");
//        try{
//            Jws<Claims> jwt = Jwts.parser().setSigningKey(Constant.SIGNING_KEY).parseClaimsJws(token);
//            Claims claims = jwt.getBody();
//            Long loginTime = (Long) claims.get("loginTime");
//            Integer expireTime = (Integer) claims.get("expireTime");
//            long l = System.currentTimeMillis();
//            long min = (l - loginTime) / 1000 / 60;
//            if (min > expireTime) {
//                throw new MyException("登录已失效");
//            }
//        }catch (Exception e) {
//            throw new MyException("登录验证失败");
//        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
