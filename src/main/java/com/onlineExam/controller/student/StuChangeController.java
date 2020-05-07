package com.onlineExam.controller.student;

import com.onlineExam.domain.*;
import com.onlineExam.service.MyCollectionService;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.StuUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Api(tags = "学生信息接口")
@Controller
//@RequestMapping("/student")
public class StuChangeController {
    //属性注入
    @Autowired
    private StuUserService stuUserService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private MyCollectionService myCollectionService;

    @RequestMapping("/stupersonal")
    public String stuPersonal(){
       return "student/personInfo";
    }
    @RequestMapping("/updateStuPass")
    public String updatePass(){
        return "student/changepwd";
    }

    @RequestMapping("/kTouch")
    public String kTouch(){
        return "student/kTouch";
    }
    //    //修改用户的信息
    @RequestMapping(value = "/ChangeStuInfo" ,method=RequestMethod.POST)
    public String ChangeInformation(HttpServletRequest request, HttpSession session, Model model,
             Integer clazz, String phone,Integer major,Integer dept ,String email) throws Exception {
        String username=(String)request.getSession().getAttribute("username");

        Student stuInformation=new Student();
        stuInformation.setUsername(username);
        stuInformation.setClazzId(clazz);
        stuInformation.setStuphonenumber(phone);
        stuInformation.setMajorId(major);
        stuInformation.setDepartId(dept);
        stuInformation.setStuemail(email);

        boolean changeInformation= stuUserService.updateInformation(stuInformation);

        if(changeInformation){
            model.addAttribute("msg", "修改成功");
        }
        else {
            model.addAttribute("msg", "修改失败");
        }
        //return "student/changeInformation";
        return "student/personInfo";
    }

    //修改密码，先加密认证原密码，再对加密新密码进行修改
    @RequestMapping("/ChangeStuPwd")
    public String ChangePassword(String password1, String password2, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Md5Hash md5password = new Md5Hash(password1, username, 5);
        String userpassword = md5password.toString();   //加密后的原密码
        //System.out.println("执行修改密码操作：-------加密后的原密码:"+userpassword);
        LoginUser user = stuUserService.findByUsername(username);

        LoginUser user2 = new LoginUser();
        if (userpassword.equals(user.getPassword())) {
            Md5Hash md5password2 = new Md5Hash(password2, username, 5);  //新密码加密后
            user2.setUsername(username);
            user2.setPassword(md5password2.toString());
            //System.out.println("执行修改密码操作：------新密码："+password2+"   ;新密码加密后:"+md5password2.toString());

            stuUserService.updatePassword(user2);
            Student student=new Student();
            student.setUsername(username);
            student.setStupassword(md5password2.toString());
            stuUserService.updateStuPassword(student);
            System.out.println("修改密码后数据库中加密密码："+user2.getPassword());
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "student/changepwd";
    }
    //退出登录
    @RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public String logout(HttpServletRequest request){

        Enumeration em=request.getSession().getAttributeNames();
        while (em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "index";
    }

    //错题本
    @RequestMapping("/myerror")
    public String myError(HttpSession session,Model model){
        Integer stuId=(Integer)session.getAttribute("stuid");
        List<StuAnswer> stuAnswerList= stuUserService.getErrorQuestion(stuId); //根据学号查找该学生答题情况
        model.addAttribute("stuAnswerList",stuAnswerList);
        return "student/errorQuestion";
    }
    @RequestMapping("/removeErrors")
    public String removeError(Integer questionId){
        boolean flag= stuUserService.removeError(questionId);
        return "student/errorQuestion";
    }
    @RequestMapping(value="/uploadImg")
    //@ResponseBody
    public String uploadFile(@RequestParam("imgName") MultipartFile file, HttpSession session, HttpServletRequest request, Model model) {

        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "error";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");
        model.addAttribute("fileName",fileName);
        //加个时间戳，尽量避免文件名称重复
        String path = "D:/fileUpload/" +fileName;
        //String path = "D:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            String headerurl="http://localhost:8080/images/"+fileName;//本地运行项目
            request.getSession().setAttribute("headerurl",headerurl);
            String username=(String)session.getAttribute("username");
            int rs= stuUserService.insertUrl(headerurl,username);
            //System.out.print("保存的完整url===="+headerurl+"\n");

        } catch (IOException e) {
            return "error";
        }
        return "student/personInfo";
    }




    // 我的收藏
    @GetMapping(value = "/collection")
    public String getIndex(HttpSession session, Model model) {
        Integer stuId = (Integer) session.getAttribute("stuid");
        List<MyCollection> myCollections = myCollectionService.getList(stuId);
        model.addAttribute("myCollections", myCollections);
        return "student/myCollection";
    }

    @GetMapping(value = "/collection/add")
    @ResponseBody
    public ResponseData add(HttpSession session,
                            @RequestParam(value = "questionId") Integer questionId) {
        Integer stuId = (Integer) session.getAttribute("stuid");
        MyCollection myCollection =
                MyCollection.builder().questionid(questionId).userid(stuId).createtime(new Date()).build();
        myCollectionService.add(myCollection);
        return ResponseData.success();
    }

    @GetMapping(value = "/collection/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        myCollectionService.del(id);
        return ResponseData.success();
    }

}
