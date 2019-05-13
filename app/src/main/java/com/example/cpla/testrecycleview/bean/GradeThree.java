package com.example.cpla.testrecycleview.bean;

import java.util.ArrayList;

public class GradeThree {

    public int classId;
    public boolean isExpand;
    public ArrayList<Student> sList;

    public GradeThree(int classId, ArrayList<Student> sList, boolean isExpand) {
        this.classId = classId;
        this.sList = sList;
        this.isExpand = isExpand;
    }

}
