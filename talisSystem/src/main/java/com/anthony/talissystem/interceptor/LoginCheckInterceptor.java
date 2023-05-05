package com.anthony.talissystem.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.anthony.talissystem.pojo.Result;
import com.anthony.talissystem.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前,返回true,放行,放回false,不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
       //1、获取请求url
        String url = req.getRequestURI().toString();
        log.info("请求的url:{}",url);

        //2、判断请求url中是否包含login,包含则说明是登陆操作并放行
        if(url.contains("login")){
            log.info("登陆操作,放行..");
            return true;
        }

        //3、获取请求头中的令牌(token)
        String jwt = req.getHeader("token");

        //4、判断令牌是否存在,若不存在,返回错误结果(未登录)
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换:对象->json(使用Aliyun工具类)
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //5、解析token,若解析失败,返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录用户信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换:对象->json(使用Aliyun工具类)
            String notLogin =JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //6、放行
        log.info("令牌合法,放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override//视图渲染完毕后运行,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}