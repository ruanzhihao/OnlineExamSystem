package com.onlineExam.controller.admin;

import com.onlineExam.domain.Major;
import com.onlineExam.domain.ResponseData;
import com.onlineExam.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping()
    public String getIndex() {
        return "admin/major/list";
    }

    @GetMapping(value = "/add.html")
    public String addPage() {
        return "admin/major/add";
    }

    @GetMapping(value = "/edit.html")
    public String editPage() {
        return "admin/major/edit";
    }

    @GetMapping(value = "/detail.html")
    public String detailPage() {
        return "admin/major/detail";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseData getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "departId", required = false) Integer departId,
                                @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(majorService.getList(pageNum, pageSize, departId, keyword));
    }

    @GetMapping(value = "/select")
    @ResponseBody
    public ResponseData getSelectList(@RequestParam(value = "departId", required = false) Integer departId,
                                      @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseData.success(majorService.getList(departId, keyword));
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody Major major) {
        majorService.add(major);
        return ResponseData.success();
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody Major major) {
        majorService.edit(major);
        return ResponseData.success();
    }

    @GetMapping(value = "/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        majorService.del(id);
        return ResponseData.success();
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public ResponseData get(@RequestParam(value = "id") Integer id) {
        return ResponseData.success(majorService.get(id));
    }
}
