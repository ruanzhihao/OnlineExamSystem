package com.onlineExam.controller.admin;

import com.onlineExam.domain.Depart;
import com.onlineExam.domain.ResponseData;
import com.onlineExam.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

    @GetMapping()
    public String getIndex() {
        return "admin/depart/list";
    }

    @GetMapping(value = "/add.html")
    public String addPage() {
        return "admin/depart/add";
    }

    @GetMapping(value = "/edit.html")
    public String editPage() {
        return "admin/depart/edit";
    }

    @GetMapping(value = "/detail.html")
    public String detailPage() {
        return "admin/depart/detail";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseData getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(departService.getList(pageNum, pageSize, keyword));
    }

    @GetMapping(value = "/select")
    @ResponseBody
    public ResponseData getSelectList(@RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(departService.getList(keyword));
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody Depart depart) {
        departService.add(depart);
        return ResponseData.success();
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody Depart depart) {
        departService.edit(depart);
        return ResponseData.success();
    }

    @GetMapping(value = "/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        departService.del(id);
        return ResponseData.success();
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public ResponseData get(@RequestParam(value = "id") Integer id) {
        return ResponseData.success(departService.get(id));
    }
}
