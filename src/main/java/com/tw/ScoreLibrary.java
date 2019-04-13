package com.tw;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LP
 * @date 2019.04.12
 */

public class ScoreLibrary {

   //singleton
    private static ScoreLibrary ins = null;
    public static ScoreLibrary getIntance(){
        if(ins == null){
            ins = new ScoreLibrary();
        }
        return  ins;
    }

    private ScoreLibrary(){
        strategy.put("少数民族",10.0);
        strategy.put("体育特长",20.0);
        strategy.put("艺术特长",15.0);
    }
    //学生
    private Hashtable<Integer,Student>students = new Hashtable<Integer,Student>();
    //加分策略
    private Map<String,Double> strategy = new Hashtable<String,Double>();
    /***
     *  成绩单
     *  班级：{学生：{"科目":成绩}}
     */
    private Hashtable<Integer,Hashtable<Student,Hashtable<String,Double>>>scores = new Hashtable<Integer,Hashtable<Student,Hashtable<String,Double>>>();

    private Map<Integer,List<Double>> klassTotalInfo = new Hashtable<Integer,List<Double>>();

    public void addStudent(Student stu){
        students.put(stu.getId(),stu);
    }

    /**
     * 添加成绩
     * @param student 学生
     * @param stuScores 成绩对 （学科：成绩）
     */
    public  void addScores(Student student, Hashtable<String,Double> stuScores){

        if(!scores.containsKey(student.getKlass()))
            scores.put(student.getKlass(),new Hashtable<Student,Hashtable<String,Double>>());

        Hashtable<Student,Hashtable<String,Double>>scores4Klass = scores.get(student.getKlass());
        scores4Klass.put(student,new Hashtable<String,Double>());
        double sum = 0;
        for(String key : stuScores.keySet()){
            sum += stuScores.get(key).intValue();
            scores4Klass.get(student).put(key,stuScores.get(key));
        }
        sum = addAddition(student,sum);
        scores4Klass.get(student).put(ConstantConfig.TOTAL,sum);
        scores4Klass.get(student).put(ConstantConfig.AVG,sum/(ConstantConfig.ELEMENT_NUMBER - ConstantConfig.ELEMENT_NUMBER));
    }

    /**
     *
     * @param ids：学号
     * @param klassInfos：返回每个班级的总分平均分和中位数信息
     * @param studentsInfos：返回每个班级每个学生的信息
     */
    public void searchStudentScore(List<Integer> ids,Map<Integer,List<Double>>klassInfos,Map<Integer,List<String>>studentsInfos){

        for(int id :ids){
            Student current = students.get(id);
            if(current != null) {
                int klass = current.getKlass();
                if(!klassInfos.containsKey(klass)) {
                    klassInfos.put(klass, new ArrayList<Double>(klassTotalInfo.get(klass)));
                    studentsInfos.put(klass, new ArrayList<String>());
                }

                Map<String, Double> currentScores = scores.get(current.getKlass()).get(current);
                List<String> currentRes = new ArrayList<String>();
                currentRes.add(current.getName());


                for (int i=1;i<ConstantConfig.HEADER.length;i++) {
                    currentRes.add(currentScores.get(ConstantConfig.HEADER[i]).toString());
                }
                studentsInfos.get(klass).add(currentRes.stream().reduce((a,b)->a+"|"+b).get());
            }
        }
    }

    /**
     * 根据学生情况加分
     * @param stu  学生
     * @param sum  未加分前总分
     * @return     最后总分
     */
    private double addAddition(Student stu,double sum){
        if(strategy.containsKey(stu.getsType()))
            sum += strategy.get(stu.getsType());
        return sum;
    }

    /**
     * 更新每个班级的总分平均分和中位数信息。
     */
    private void updateKlassTotalInfo( ){

        for(int klass : scores.keySet()){

            if(!klassTotalInfo.containsKey(klass))
                klassTotalInfo.put(klass,new ArrayList<Double>());

            List<Double> totalScores = new ArrayList<Double>();

            for(Student stu : scores.get(klass).keySet())
                totalScores.add(scores.get(klass).get(stu).get(ConstantConfig.TOTAL));

            totalScores.sort(Double::compareTo);

            //获取中位数
            int idx = totalScores.size()/2;
            if(totalScores.size()%2 == 0) {
                klassTotalInfo.get(klass).set(1,(totalScores.get(idx)+totalScores.get(idx-1))/2 );
            }else{
                klassTotalInfo.get(klass).set(1,totalScores.get(idx));
            }
            //获取平均数
            double avg = totalScores.stream().reduce((a,b)->a+b).get()/totalScores.size();
            klassTotalInfo.get(klass).set(0,avg);

        }

    }


}
