package com.tw;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * @author LP
 * @date 2019.04.12
 */

public class UserInteraction {

    Scanner reader = new Scanner(System.in);

    public void mainMenus(){

        System.out.println("1. 添加学生");
        System.out.println("2. 生成成绩单");
        System.out.println("3. 退出请输入你的选择（1～3）：");

        handleSelection();

    }

    public void handleSelection(){
       int seletion = reader.nextInt();

       switch (seletion){
           case 1:
               addStudentMenu();
               break;
           case 2:
               searchMenu();
               break;
           case 3:
               System.exit(0);
               break;
          default:
              System.out.println("请输入1-3的有限字符");
              break;
        }
        mainMenus();
    }

    public  void  addStudentMenu(){

        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        handleSelection();
    }


    public  void  searchMenu(){
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");

    }
    public void handleddStudent(ScoreLibrary library){

        String inputStr = reader.nextLine();

        try{
            String []  elems = inputStr.split(",");

            if(elems.length <4){//缺乏成绩信息等

                throw new Exception("length error");

            } else {

                Student student = new Student(elems[0],Integer.parseInt(elems[1]),elems[2],Integer.parseInt(elems[3]));
                Hashtable<String,Integer> scores = new Hashtable<String,Integer>();

                for(int i=4; i<elems.length;i++){
                    int key =0,value = 1;
                    String[] map = elems[i].split(":");
                    scores.put(map[key],new Integer(map[value]));
                }
                //add
                library.addScores(student,scores);
                library.addStudent(student);

                System.out.println(String.format("学生%s的成绩被添加",student.getName()));
            }

        }catch(Exception e){
            printFormatError();
            addStudentMenu();
        }
    }

    public void printFormatError(){
        System.out.println("请按正确的格式输入（格式：姓名, 学号, 民族, 班级, 学科: 成绩, ...）");
    }
}
