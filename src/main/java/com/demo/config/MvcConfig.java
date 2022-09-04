package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //處理頁面跳轉
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/register").setViewName("register");

        registry.addViewController("/addEmp").setViewName("addEmp");

        registry.addViewController("/PasswordNotCorrectException").setViewName("Exception/PasswordNotCorrectException");

        registry.addViewController("/UserNameNotFoundException").setViewName("Exception/UserNameNotFoundException");

        registry.addViewController("/UnknownException").setViewName("Exception/UnknownException");

        registry.addViewController("/UserNameBeRegisteredException").setViewName("Exception/UserNameBeRegisteredException");

        registry.addViewController("/VerifyCodeNotCorrectException").setViewName("Exception/VerifyCodeNotCorrectException");
    }

    //攔截未登入的請求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/login","/register","/user/**","/static/**","/**Exception","/css/**")
                .addPathPatterns("/**");

    }

}
