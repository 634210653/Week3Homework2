package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HandlerTest {

    private  Handler handler = null;
    @Before
    public void initHandler(){
        handler = new Handler();
    }

    @Test
    public void handleAddStudentTest() {

        StringBuffer  studentName = new StringBuffer();
        String  inputStr1 = "张三,1101,正常,12,语文:100,英语:100,数学:100,编程:90";

        assertTrue(handler.handleAddStudent(inputStr1,studentName));
        assertEquals("张三",studentName.toString());

        String  inputStr2 = "张三,x1101,正常-12,语文:100,英语:100,数学:100,编程:90";
        assertFalse( handler.handleAddStudent(inputStr2,studentName));

    }

    @Test
    public void handleSearchTest() {

        StringBuffer  studentName = new StringBuffer();
        String  inputStr1 = "张三,1101,正常,12,语文:100,英语:100,数学:100,编程:90";
        assertTrue(handler.handleAddStudent(inputStr1,studentName));

        String  inputStr2 = "1101,1101";
        String  inputStr3 = "1101,1102";

        Map<Integer, List<Double>> klassInfos1 = new Hashtable<>();
        Map<Integer,List<String>>studentsInfo1 = new Hashtable<>();
        Map<Integer, List<Double>> klassInfos2 = new Hashtable<>();
        Map<Integer,List<String>>studentsInfo2 = new Hashtable<>();
        assertTrue(handler.handleSearch(inputStr2,klassInfos1,studentsInfo1));
        assertTrue(handler.handleSearch(inputStr3,klassInfos2,studentsInfo2));
        assertEquals(klassInfos1,klassInfos2);
        assertEquals(studentsInfo1,studentsInfo2);


    }
}