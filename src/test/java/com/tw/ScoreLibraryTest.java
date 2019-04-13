package com.tw;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ScoreLibraryTest {


    @Test
    public void testMainMethod() {
        ScoreLibrary library = ScoreLibrary.getIntance();
        Student zha = new Student("张三",133,"艺术特长",12);
        Student li =  new Student("李四",123,"体育特长",12);
        Student wan =  new Student("老王",111,"正常",13);
        Hashtable<String,Double> map = new Hashtable<String,Double>();

        map.put("语文",30.0);
        map.put("英语",30.0);
        map.put("数学",50.0);
        map.put("编程",100.0);

        library.addStudent(zha);
        library.addStudent(li);
        library.addStudent(wan);

        library.addScores(zha,map);
        library.addScores(li,map);
        library.addScores(wan,map);

        ArrayList<Integer> ids1 = new ArrayList<>();
        ids1.add(123);
        ids1.add(133);

        Hashtable<Integer, List<Double>> klassTotalInfo1 = new Hashtable<>();
        Hashtable<Integer, List<Double>> klassTotalInfo2 = new Hashtable<>();
        Hashtable<Integer, List<Double>> klassTotalInfo3 = new Hashtable<>();

        Hashtable<Integer, List<String>> studentInfos1 = new Hashtable<>();
        Hashtable<Integer, List<String>> studentInfos2 = new Hashtable<>();
        Hashtable<Integer, List<String>> studentInfos3 = new Hashtable<>();

        library.searchStudentScore(ids1,klassTotalInfo1,studentInfos1);
        ids1.remove(1);
        library.searchStudentScore(ids1,klassTotalInfo2,studentInfos2);
        ids1.clear();
        ids1.add(111);
        library.searchStudentScore(ids1,klassTotalInfo3,studentInfos3);

        assertEquals(klassTotalInfo1,klassTotalInfo2);
        assertEquals("李四|50.0|30.0|30.0|100.0|57.5|230.0",studentInfos1.get(12).get(0));
        assertEquals("张三|50.0|30.0|30.0|100.0|56.25|225.0",studentInfos1.get(12).get(1));
        assertEquals("李四|50.0|30.0|30.0|100.0|57.5|230.0",studentInfos2.get(12).get(0));
        assertEquals("老王|50.0|30.0|30.0|100.0|52.5|210.0",studentInfos3.get(13).get(0));
    }

}