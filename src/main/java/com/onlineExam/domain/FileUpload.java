package com.onlineExam.domain;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedList;
import java.util.List;

public class FileUpload {

    private static int questionNameIndex;
    private static int optionAIndex;
    private static int optionBIndex;
    private static int optionCIndex;
    private static int optionDIndex;
    private static int answerIndex;
    private static int questionScoreIndex;
    private static int questionTypeIndex;
    private static int questionClassIndex;

    public static List<Question> getFile(String filePath, Integer courseId, Integer majorId) {
        List<Question> questions = new LinkedList<Question>();
        try {
            //读取工作本
            XSSFWorkbook workBook = new XSSFWorkbook(filePath);
            //读取工作簿
            XSSFSheet sheet = workBook.getSheet("Sheet1");
            //总行数
            int sumRow = sheet.getLastRowNum()-sheet.getFirstRowNum();

            XSSFRow firstRow = sheet.getRow(0);
            getCellIndexs(firstRow);

            for (int i = 1; i <= sumRow; i++) {
                XSSFRow row = (XSSFRow) sheet.getRow(i);
                XSSFCell questionName = row.getCell(questionNameIndex);
                XSSFCell optionA = row.getCell(optionAIndex);
                XSSFCell optionB = row.getCell(optionBIndex);
                XSSFCell optionC = row.getCell(optionCIndex);
                XSSFCell optionD = row.getCell(optionDIndex);
                XSSFCell answer = row.getCell(answerIndex);
                XSSFCell questionScore = row.getCell(questionScoreIndex);
                if(questionScore.getCellType()==0) { questionScore.setCellType(1); }
                XSSFCell questionType = row.getCell(questionTypeIndex);
                XSSFCell questionClass = row.getCell(questionClassIndex);

                Question question = new Question();
                question.setQuestionName(questionName.toString());

                question.setOptionA(optionA == null ? "" : optionA.toString());
                question.setOptionB(optionB == null ? "" : optionB.toString());
                question.setOptionC(optionC == null ? "" : optionC.toString());
                question.setOptionD(optionD == null ? "" : optionD.toString());

                question.setAnswer(answer.toString());
                question.setQuestionScore(Integer.parseInt(questionScore.toString()));
                //试题类型
                if("单选".equals(row.getCell(questionTypeIndex).toString())){
                    question.setQuestionType("单选");
                }else if("多选".equals(row.getCell(questionTypeIndex).toString())){
                    question.setQuestionType("多选");
                }else{
                    question.setQuestionType("其他");
                }

                if ("简单".equals(questionClass.toString())) {
                    question.setQuestionClass("简单");
                } else if ("中等".equals(questionClass.toString())) {
                    question.setQuestionClass("中等");
                } else {
                    question.setQuestionClass("困难");
                }
                question.setCourseId(courseId);
                question.setMajorId(majorId);


                questions.add(question);
            }

            workBook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }


    /**
     * 解析第一行标题名得到列索引
     * @param firstRow
     */
    private static void getCellIndexs(XSSFRow firstRow) {
        int cellNum = firstRow.getLastCellNum()-firstRow.getFirstCellNum();
        for (int i=0; i<cellNum; i++) {
            String cell = firstRow.getCell(i).toString();
            if ("题目".equals(cell)) {
                questionNameIndex = i;
                continue;
            }
            if ("答案A".equals(cell)) {
                optionAIndex = i;
                continue;
            }
            if ("答案B".equals(cell)) {
                optionBIndex = i;
                continue;
            }
            if ("答案C".equals(cell)) {
                optionCIndex = i;
                continue;
            }
            if ("答案D".equals(cell)) {
                optionDIndex = i;
                continue;
            }
            if ("正确答案".equals(cell)) {
                answerIndex = i;
                continue;
            }
            if ("分值".equals(cell)) {
                questionScoreIndex = i;
                continue;
            }
            if ("试题类型".equals(cell)) {
                questionTypeIndex = i;
                continue;
            }
            if ("难易程度".equals(cell)) {
                questionClassIndex = i;
                continue;
            }
        }
    }
}
