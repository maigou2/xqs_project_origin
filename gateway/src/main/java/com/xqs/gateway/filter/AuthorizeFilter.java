//package com.xqs.gateway.filter;
//
//import com.xqs.commoncore.constens.Constant;
//import com.xqs.commoncore.enums.ResultEnum;
//import com.xqs.commoncore.exception.MyException;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Objects;
//
//@Component
//public class AuthorizeFilter implements Ordered, GlobalFilter {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //1.获取请求
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//
//        //2.判断是否是登录操作
//        if (request.getURI().getPath().contains("/login/login") || request.getURI().getPath().contains("/login/register")) {
//            //放行
//            return chain.filter(exchange);
//        }
//
//        String token = request.getHeaders().getFirst("token");
//        //3.若token为空，校验失败
//        if (StringUtils.isEmpty(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //结束请求
//            throw new MyException(ResultEnum.NOT_LOGIN.getCode(), "登录已失效");
//        }
//        try {
//            Jws<Claims> jwt = Jwts.parser().setSigningKey(Constant.SIGNING_KEY).parseClaimsJws(token);
//            Claims claims = jwt.getBody();
//            Long loginTime = (Long) claims.get("loginTime");
//            Integer expireTime = (Integer) claims.get("expireTime");
//            long l = System.currentTimeMillis();
//            long min = (l - loginTime) / 1000 / 60;
//            if (min > expireTime) {
//                throw new MyException(ResultEnum.NOT_LOGIN.getCode(), "登录已失效");
//            }
//            // 将用户信息存放进 header中
////            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
////                httpHeaders.add("user", user);
////            }).build();
//            ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
//            }).build();
//            exchange.mutate().request(serverHttpRequest).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //5.放行
//        return chain.filter(exchange);
//    }
//
//    /**
//     * 优先级设置，值越小 优先级越高
//     *
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}