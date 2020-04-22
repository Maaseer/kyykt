package com.zstu.ky.kyykt.zuul.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Object principal=authentication.getPrincipal();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String ,Object> map = new HashMap<>();
        response.setStatus(200);
        map.put("status",200);
        map.put("msg",principal);
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
