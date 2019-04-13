package com.tw;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LP
 * @date 2019.04.12
 */

public class Handler {

    private final UI ui = new UI();
    private final ScoreLibrary  library = ScoreLibrary.getIntance();

    public void HandleMainMenus(){

        while(true) {

          ui.printMainMenus();
          int seletion = ui.getInt();

          switch (seletion) {
              case 1:
                  handleAddStudent();
                  break;
              case 2:
                  handleSearch();
                  break;
              case 3:
                  System.exit(0);
                  break;
              default:
                  ui.printSearchFormatError();
                  break;
          }
      }//while
    }

    public void handleAddStudent(){

        int key = 0, value = 1;

        while(true) {

            ui.printAddStudentMenu();
            String inputStr = ui.getString();

            try {
                String[] elems = inputStr.split(",");

                if (elems.length < 4) //缺乏成绩信息等
                    throw new Exception("length error");

                Student student = new Student(elems[0], Integer.parseInt(elems[1]), elems[2], Integer.parseInt(elems[3]));
                Hashtable<String, Integer> scores = new Hashtable<String, Integer>();

                for (int i = 4; i < elems.length; i++) {
                    String[] map = elems[i].split(":");
                    scores.put(map[key], new Integer(map[value]));
                }

                library.addScores(student, scores);
                library.addStudent(student);
                ui.printlnAddStudentResult(student.getName());
                break;
            } catch (Exception e) {
                ui.printAddFormatError();
            }
        }
    }

    public void handleSearch(){

        while(true) {

           ui.printSearchMenu();
            String inputStr = ui.getString();

            try {
                String[] elems = inputStr.split(",");
                List<Integer> ids = Stream.of(elems).map(Integer::new).collect(Collectors.toList());

                Hashtable<Student, Hashtable<String, Integer>> res = library.searchStudentScore(ids);
                ui.printSearchResult(res);
                break;
            } catch (Exception e) {
                ui.printSearchFormatError();
            }
        }
    }


}
