package com.onlineExam.domain;

import lombok.Data;

/*人脸识别插入数据库实体类
 * */
@Data
public class FaceUsers {
  private int id;
  private String username;
  private String face;
}
