package com.onlineExam.controller;

import com.onlineExam.domain.MyCollection;
import com.onlineExam.domain.ResponseData;
import com.onlineExam.service.MyCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/my/collection")
public class MyCollectionController {

    @Autowired
    private MyCollectionService myCollectionService;

    @GetMapping()
    public String getIndex(Model model,
                           @RequestParam(value = "userId") Integer userId) {
        model.addAttribute("userId", userId);
        return "myCollection";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseData getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "userId", defaultValue = "0") Integer userId) {
        return ResponseData.success(myCollectionService.getList(pageNum, pageSize, userId));
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody MyCollection myCollection) {
        myCollectionService.add(myCollection);
        return ResponseData.success();
    }

    @GetMapping(value = "/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        myCollectionService.del(id);
        return ResponseData.success();
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public ResponseData get(@RequestParam(value = "id") Integer id) {
        return ResponseData.success(myCollectionService.get(id));
    }
}
