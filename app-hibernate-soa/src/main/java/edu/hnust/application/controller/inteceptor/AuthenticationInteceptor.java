package edu.hnust.application.controller.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.hnust.application.util.Md5Util;

/**
 * SOA拦截器
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年11月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuthenticationInteceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        System.out.println(new Md5Util(token).toMD5());
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception {
        
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        
    }
}