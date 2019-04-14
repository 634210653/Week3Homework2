package com.tw;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void equalsTest() throws Exception {

      Student stu1 = new Student("张三",1,"正常",1);
      Student stu2 = new Student("张三",2,"正常",1);
      assertEquals(stu1.equals(stu1),true);
      assertEquals(stu1.equals(stu2),false);
    }



}