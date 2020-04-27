package com.onlineExam.controller.admin;

import com.onlineExam.domain.Clazz;
import com.onlineExam.domain.ResponseData;
import com.onlineExam.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping()
    public String getIndex() {
        return "admin/clazz/list";
    }

    @GetMapping(value = "/add.html")
    public String addPage() {
        return "admin/clazz/add";
    }

    @GetMapping(value = "/edit.html")
    public String editPage() {
        return "admin/clazz/edit";
    }

    @GetMapping(value = "/detail.html")
    public String detailPage() {
        return "admin/clazz/detail";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseData getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "departId", required = false) Integer departId,
                                @RequestParam(value = "majorId", required = false) Integer majorId,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(clazzService.getList(pageNum, pageSize, departId, majorId, keyword));
    }

    @GetMapping(value = "/select")
    @ResponseBody
    public ResponseData getSelectList(@RequestParam(value = "departId", required = false) Integer departId,
                                      @RequestParam(value = "majorId", required = false) Integer majorId,
                                      @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(clazzService.getList(departId, majorId, keyword));
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody Clazz clazz) {
        clazzService.add(clazz);
        return ResponseData.success();
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody Clazz clazz) {
        clazzService.edit(clazz);
        return ResponseData.success();
    }

    @GetMapping(value = "/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        clazzService.del(id);
        return ResponseData.success();
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public ResponseData get(@RequestParam(value = "id") Integer id) {
        return ResponseData.success(clazzService.get(id));
    }

}
