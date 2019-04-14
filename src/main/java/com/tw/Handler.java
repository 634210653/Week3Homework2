package com.tw;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LP
 * @date 2019.04.12
 */

public class Handler {


    private final ScoreLibrary  library = ScoreLibrary.getIntance();


    /**
     * 处理添加学生成绩逻辑
     */
    public boolean handleAddStudent(String inputStr,String outputStr){

        int key = 0, value = 1;

        try {
            String[] elems = inputStr.split(",");

            if (elems.length != ConstantConfig.ELEMENT_NUMBER)
                throw new Exception("length error");

            Student student = new Student(elems[0], Integer.parseInt(elems[1]), elems[2], Integer.parseInt(elems[3]));

            Hashtable<String, Double> scores = new Hashtable<String, Double>();
            for (int i = ConstantConfig.STUDENT_FIELD_NUMBER; i < elems.length; i++) {
                String[] map = elems[i].split(":");
                scores.put(map[key], new Double(map[value]));
            }

            library.addScores(student, scores);
            library.addStudent(student);
            outputStr = student.getName();
            return  true;

        } catch (Exception e) {
            return  false;
        }
    }

    /**
     *处理查询逻辑
     */
    public boolean handleSearch(String inputStr,  Map<Integer,List<Double>>klassInfos, Map<Integer,List<String>>studentsInfo){

            try {

                String[] elems = inputStr.split(",");
                List<Integer> ids = Stream.of(elems).map(Integer::new).collect(Collectors.toList());
                library.searchStudentScore(ids,klassInfos,studentsInfo);
                return true;

            } catch (Exception e) {
                return false;
            }
    }

}
