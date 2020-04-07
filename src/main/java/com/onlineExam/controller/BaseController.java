package com.onlineExam.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    public static HttpServletRequest getHttpRequest(){
        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }
}
