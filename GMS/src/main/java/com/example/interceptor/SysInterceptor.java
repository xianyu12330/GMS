package com.example.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.result.Code;
import com.example.result.Result;

import com.example.gms.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
/*本拦截器用于进入系统的token验证与设置*/
public class SysInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Result ResultMsg = null;
        // 获取请求头中的token验证字符串
        String headerToken = request.getHeader("token");
        // 非登录与注册界面即拦截
        if (!request.getRequestURI().contains("login")) {
            if (headerToken == null) {
                // 如果没有token,设置错误信息
                ResultMsg = new Result(Code.TOKEN_ERR, "token Missing!!!");
            } else {
                try {
                    // 更新与验证旧token
                    headerToken = JWTUtil.updateToken(headerToken);
                } catch (Exception e) {
                    // 若出现错误（验证token步骤）
                    ResultMsg = new Result(Code.TOKEN_ERR, "Illegal Token!!!");
                }
                //下面为用户请求页面允许进入的拦截
                Claims claims = JWTUtil.verifyToken(headerToken);
                //获得权限
                String Authority = claims.getIssuer();

               /* // 拦截getUserList接口
                if (request.getRequestURI().contains("user")) {
                    System.out.println(claims);
                    System.out.println(Authority);
                    System.out.println(Authority);
                    System.out.println(claims.getId());
                    if (!Authority.equals("1")) {
                        response.getWriter().write(JSON.toJSONString(new Result(Code.TOKEN_ERR, "AUTHORITY_LIMITED")));
                    }
                }*/
            }

        }
        // 如果有错误信息
        if (ResultMsg != null) {
            response.getWriter().write(JSON.toJSONString(ResultMsg));
            return false;
        } else {
            // 将更新的token加入返回的header中
            response.setHeader("token", headerToken);
            return true;
        }
    }
}