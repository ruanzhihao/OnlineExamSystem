package com.onlineExam.controller;

import com.onlineExam.domain.FileEntity;
import com.onlineExam.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class MyFileController {
    @Autowired
    private FileService fileService;
    private String url;
    @RequestMapping(value="/uploadFile")
    //@ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file, HttpSession session, HttpServletRequest request) {

        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "error";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");
        //String createtime=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");

        String createtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //加个时间戳，尽量避免文件名称重复
        String path = "C:/fileUpload/" +fileName;
        //String path = "C:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "error";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url="http://localhost:8080/images/"+fileName;//本地运行项目
            System.out.println("url:"+url);
            String uploader=(String)request.getSession().getAttribute("teachername");
            FileEntity fileEntity=new FileEntity();
            fileEntity.setTitle(fileName);
            fileEntity.setPath(path);
            fileEntity.setUrl(url);
            fileEntity.setCreatetime(createtime);
            fileEntity.setUploader(uploader);
            fileEntity.setState("未完成");
            int jieguo= fileService.addUrl(fileEntity);
            //System.out.print("插入结果"+jieguo+"\n");
            //System.out.print("保存的完整url: "+url+"\n");
            //System.out.println("上传者："+uploader);

        } catch (IOException e) {
            return "error";
        }
        return "redirect:/chaxun";
    }

    //查询
    @RequestMapping("/chaxun")
    public String huizhiDuanxin(Model model){
        System.out.print("查询视频"+"\n");
        List<FileEntity> files=fileService.fileList();
        System.out.print("查询到的视频数量=="+files.size()+"\n");
        model.addAttribute("Files", files);

        return "filelist";
    }
    //查询
    @RequestMapping("/stuchaxun")
    public String stuChaxun(Model model){
        List<FileEntity> files=fileService.fileList();
        System.out.print("查询到的视频数量=="+files.size()+"\n");
        model.addAttribute("Files", files);
        return "student/studytasks";
    }
    //删除
    @RequestMapping("/removefile")
    public String removeFile(Integer id){
        int rs=fileService.removeFile(id);
        return "filelist";
    }
    //状态
    @RequestMapping("/stustate")
    public String stuState(Integer id){
        int result=fileService.stuState(id);
        return "student/studytasks";
    }


}
