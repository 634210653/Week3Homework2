package com.tw;

/**
 * @author LP
 * @date 2019.04.12
 */

//学生实体
public class Student {

    private  String name;
    private  int   id;
    private  String sType;
    private  int   klass;

    public Student(String name, int id, String sType,int klass){
        this.name = name;
        this.id = id;
        this.sType = sType;
        this.klass = klass;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getsType() {
        return sType;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null)
            return false;

        if(((Student)obj).getId() == this.id)
            return  true;

        return false;
    }

    public int getKlass() {
        return klass;
    }
}
