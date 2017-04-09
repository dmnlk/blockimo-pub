package com.dmnlk.blockimo.interceptor;

import com.dmnlk.blockimo.annotaion.NonAuth;
import com.dmnlk.blockimo.dao.TAccountDao;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * login check
 */
@Log4j
public class SampleInterceptor implements HandlerInterceptor {
    @Autowired
    private TwitterAuthDto twitterAuthDto;
    @Autowired
    private TAccountDao tAccountDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        String requestURI = request.getRequestURI();
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        NonAuth annotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
        if (annotation != null) {
            return true;
        }
        if (twitterAuthDto == null) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }

        if (twitterAuthDto.isLogin() == false) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && twitterAuthDto != null && twitterAuthDto.isLogin()) {
            modelAndView.addObject("isLogin", Boolean.TRUE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
