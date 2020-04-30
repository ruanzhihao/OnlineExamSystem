package com.onlineExam.controller;

import com.baidu.aip.face.AipFace;
import com.onlineExam.domain.FaceUsers;
import com.onlineExam.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class FaceController {
    @RequestMapping(value = "/addFace")
    public String addFace(@RequestParam("stuid") int stuid, Model model){
        model.addAttribute("stuid",stuid);
        return "student/faceLogin/addFace";
    }


    @RequestMapping(value = "/faceByLogin")
    public String addFace(){
        return "student/faceLogin/faceLogin";
    }

    @RequestMapping("/registe")
    @ResponseBody
    public void registe(HttpServletRequest request, HttpServletResponse response, Model model,@RequestParam("stuid") int stuid) {
        String img = request.getParameter("img"); // 图像数据
       /* int stuid = request.getParameter("stuid"); // 用户名*/
        // 注册百度云 人脸识别 提供的信息
        String APP_ID = "18964906";
        String API_KEY = "chzzxEyOCjxcAAtGp2DzjYtG";
        String SECRET_KEY = "GvBrdSTBINRKmSesF5HBkRKpeg1FBmv3";
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        face(stuid, img, response, request, client);
        /* int i=userService.updateFaceState(username, "1"); */
    }

    /**
     *作用：     1.将base64编码的图片保存
     *        2.将图片路径保存在数据库里面
     *        3.将人脸图片识别在人脸库中注册
     */
    public void face(int stuid, String img, HttpServletResponse response, HttpServletRequest request,
                     AipFace client) {
        FaceUsers user = new FaceUsers();
        // 图片名称
        String fileName = System.currentTimeMillis() + ".png";
        String basePath = request.getSession().getServletContext().getRealPath("picture/");

        // 往数据库里面插入注册信息
        /* user.setUsername(username); */
        /* user.setFace("/picture/" + fileName); */
        String image="/picture/" + fileName;
        /* userService.insertFace(username, image); */
        // 往服务器里面上传图片
        GenerateImage(img, basePath + "/" + fileName);
        // 给人脸库中加入一个脸
        String flag = FaceAdd.add(client, fileName, stuid,img);
        try {
            PrintWriter out = response.getWriter();
            // 中文乱码，写个英文比较专业 哈哈
	           /* if (flag == false) {
	                out.print("Please aim at the camera!!");// 请把脸放好咯
	            } else {*/
            out.print("Record the success of the image!!"); // 注册成功

            /* } */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public String search(String img) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", img);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_repeat");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);

            JSONObject fromObject = JSONObject.fromObject(result);
            JSONObject resultscore = fromObject.getJSONObject("result");
            JSONArray jsonArray = resultscore.getJSONArray("user_list");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                double resultList = object.getDouble("score");
                String username=object.getString("user_info");
                System.out.println(resultList);
                /* System.out.println(username); */
                if (resultList >= 90) {
                    return username;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "失败";
    }
    /**
     * 用户人脸识别登录功能
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/facelogin")
    @ResponseBody
    public  String onListStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
        String img = request.getParameter("img"); // 图像数据
        String tag = search(img);
        try {
            PrintWriter writer = response.getWriter();
            if (tag.equals("失败")) {
                writer.print(tag);
                writer.close();
            }else {
                /* request.getSession().setAttribute("user", "张三"); */
                writer.print(tag);
                writer.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/404.jsp";
        }
        return tag;
    }


    public boolean getResult(HttpServletRequest request, String imStr1, String imgStr2) {

        String accessToken =AuthService.getAuth();
        boolean flag = false;
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            byte[] bytes1 = FileUtil.readFileByBytes("");
            byte[] bytes2 = FileUtil.readFileByBytes("");
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);


            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间，
            // 客户端可自行缓存，过期后重新获取。

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            // return result;
            JSONObject fromObject = JSONObject.fromObject(result);

            JSONArray jsonArray = fromObject.getJSONArray("result");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                double resultList = object.getDouble("score");

                if (resultList >= 90) {
                    flag = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
