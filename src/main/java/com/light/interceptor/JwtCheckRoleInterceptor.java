package com.light.interceptor;

import com.light.model.User;
import com.light.service.CommonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class JwtCheckRoleInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private CommonService commonService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.commonService = applicationContext.getBean(CommonService.class);
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        User user = commonService.getCurrentUser();
        if (user.getRole().getId() != 1) {
            response.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }

    // You can leave these methods empty if you don't need them
    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
    }
}
