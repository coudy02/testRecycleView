package com.example.cpla.testrecycleview.bean;

import java.util.List;

public class GroupObject {

    public GroupObject(int id, String groupName, List<ManuObject> objList) {
        this.id = id;
        this.groupName = groupName;
        this.objList = objList;
    }

    public int id;
    public String groupName;
    public List<ManuObject> objList;


}
