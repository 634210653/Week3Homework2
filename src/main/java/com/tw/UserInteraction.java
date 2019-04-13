package com.tw;

import java.util.Scanner;

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
               break;
           case 2:
               break;
           case 3:

               break;
          default:

              break;
        }
        mainMenus();
    }

    public void handleddStudent(){


    }
}
