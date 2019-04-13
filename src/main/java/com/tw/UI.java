package com.tw;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LP
 * @date 2019.04.12
 */

public class UI {

    Scanner reader = new Scanner(System.in);

    public String getString(){
        return reader.nextLine();
    }

    public int getInt(){
        return  reader.nextInt();
    }

    public void printMainMenus(){
        System.out.println("1. 添加学生");
        System.out.println("2. 生成成绩单");
        System.out.println("3. 退出请输入你的选择（1～3）：");
    }

    public  void  printAddStudentMenu(){
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
    }

    public  void  printSearchMenu(){
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");

    }

    public void printSearchResult( Hashtable<Student, Hashtable<String, Integer>> res){

        
    }

    public void printlnAddStudentResult(String name){
        System.out.println(String.format("学生%s的成绩被添加",name));
    }

    public void printMainMenusError(){
        System.out.println("请输入1-3的有限字符");
    }
    public void printAddFormatError(){
        System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）:");
    }

    public void printSearchFormatError(){
        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }
}
