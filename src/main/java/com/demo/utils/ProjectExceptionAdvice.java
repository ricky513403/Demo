package com.demo.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionAdvice {
    //攔截所有未被定義的異常
    @ExceptionHandler(Exception.class)
    public String doException(Exception ex){
        //紀錄日誌
        ex.printStackTrace();

        return "redirect:/UnknownException";
    }

}

