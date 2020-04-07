package com.onlineExam.controller;

public class BaseController {
    public static HttpServletRequest getHttpRequest(){
        HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }
}
