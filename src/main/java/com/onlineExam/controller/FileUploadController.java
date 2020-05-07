package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {

    @RequestMapping("/fileUpload")
    public String File(){
    return "fileUpload";
    }
    @RequestMapping("/addfile")
    public String addFile(){
        return  "filelist";
    }
}
