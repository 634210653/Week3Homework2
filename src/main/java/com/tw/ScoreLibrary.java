package com.tw;

import java.util.Hashtable;
import java.util.List;

/**
 * @author LP
 * @date 2019.04.12
 */

public class ScoreLibrary {

    //学生
    private Hashtable<Integer,Student>students = new Hashtable<Integer,Student>();
    //成绩单
    private Hashtable<Student,Hashtable<String,Integer>>scores = new Hashtable<Student,Hashtable<String,Integer>>();

    public void addStudent(Student stu){
        students.put(stu.getId(),stu);
    }

    public  void addScores(Student student, Hashtable<String,Integer> stuScores){

        if(!scores.containsKey(student))
           scores.put(student,new Hashtable<String,Integer>());

        //可以更新原分数
        for(String key : stuScores.keySet()){
            scores.get(student).put(key,stuScores.get(key));
        }
    }

    public Hashtable<Student,Hashtable<String,Integer>> searchStudentScore(List<Integer> ids){

        Hashtable<Student,Hashtable<String,Integer>> res = new Hashtable<Student,Hashtable<String,Integer>>();

        for(int id :ids){
            Student current = students.get(id);
            if(current != null && scores.containsKey(current)){
                res.put(students.get(id),scores.get(current));
            }
        }

        return  res;
    }
}
