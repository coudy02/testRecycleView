package com.example.cpla.testrecycleview;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class testJava {

    static ArrayList<GradeThree> gList = new ArrayList<>();

    static HashMap<Integer, Integer> mHeaderIndex = new HashMap<>();

    public static void main(String args[]) {

        setData();
        int count = getContentCount();
        System.out.println("count="+(count+gList.size()));

        for (Map.Entry<Integer, Integer> entry : mHeaderIndex.entrySet()) {
            System.out.println("key="+ entry.getKey());
            System.out.println("value="+entry.getValue());
        }
    }

    private static int getContentCount(){
        int itemCount = 0;
        int studentSize = 0;
        for (int i = 0; i < gList.size(); i++) {
            if(i != 0){
                itemCount++;
            }
            System.out.println("-----itemCount----"+itemCount);
            //存储第几班的index位置
            mHeaderIndex.put(i,new Integer(itemCount));
            itemCount += getStudentSizeOfClass(i);
            studentSize += getStudentSizeOfClass(i);
        }
        return studentSize;
    }

    private static int getStudentSizeOfClass(int classId){
        return gList.get(classId).sList.size();
    }

    private static void setData(){
        for(int i = 0; i < 5; i++){
            ArrayList<Student> sList = new ArrayList<>();
            for(int j = 0; j < 2; j++){
                Student s = new Student(j, "Apple_"+j);
                sList.add(s);
            }
            GradeThree g = new GradeThree(i, sList);
            gList.add(g);
        }
    }


    static class AllGrade{
        public int GradeId;
        public ArrayList<GradeThree> gList;

        public AllGrade(int GradeId, ArrayList<GradeThree> gList) {
            this.GradeId = GradeId;
            this.gList = gList;
        }
    }

    static class GradeThree{
        public int classId;
        public ArrayList<Student> sList;

        public GradeThree(int classId, ArrayList<Student> sList) {
            this.classId = classId;
            this.sList = sList;
        }
    }

    static class Student{
        public int studentId;
        public String name;

        public Student(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }
    }


}
