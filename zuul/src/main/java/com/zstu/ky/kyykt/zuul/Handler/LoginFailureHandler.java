package com.zstu.ky.kyykt.zuul.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setStatus(401);
        Map<String ,Object> map = new HashMap<>();

        map.put("status",401);
        if(e instanceof LockedException)
        {
            map.put("msg","账户被锁定，登录失败");
        }
        else if(e instanceof BadCredentialsException)
        {
            map.put("msg","用户名或密码输入错误，登录失败");
        }
        else if(e instanceof DisabledException)
        {
            map.put("msg","账户被禁用，登录失败");
        }
        else if(e instanceof AccountExpiredException)
        {
            map.put("msg","账户已过期，登录失败");
        }
        else if(e instanceof CredentialsExpiredException)
        {
            map.put("msg","密码已过期，登录失败");
        }else
        {
            map.put("msg","登录失败");
        }
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
