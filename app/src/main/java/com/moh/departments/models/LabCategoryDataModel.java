package com.moh.departments.models;

import java.util.ArrayList;

public class LabCategoryDataModel {
    private String CATEGORY_NAME;
    private String CATSTATUS;
    private int CATSTATUSID;
    private int CATEGORY_ID;
    private String CATGROUPNAME;
    private String CATGROUPID;
    private ArrayList<Object> mListTest;

    public LabCategoryDataModel(String CATEGORY_NAME, String CATSTATUS, int CATSTATUSID,
                                int CATEGORY_ID, String CATGROUPNAME, String CATGROUPID) {
        this.CATEGORY_NAME = CATEGORY_NAME;
        this.CATEGORY_ID = CATEGORY_ID;
        this.CATSTATUS = CATSTATUS;
        this.CATSTATUSID = CATSTATUSID;
        this.CATGROUPNAME = CATGROUPNAME;
        this.CATGROUPID = CATGROUPID;
        mListTest = new ArrayList<>();
    }

    public String getCATGROUPNAME() {
        return CATGROUPNAME;
    }

    public void setCATGROUPNAME(String CATGROUPNAME) {
        this.CATGROUPNAME = CATGROUPNAME;
    }

    public String getCATGROUPID() {
        return CATGROUPID;
    }

    public void setCATGROUPID(String CATGROUPID) {
        this.CATGROUPID = CATGROUPID;
    }

    public ArrayList<Object> getmListTest() {
        return mListTest;
    }

    public void setmListTest(ArrayList<Object> mListTest) {
        this.mListTest = mListTest;
    }

    public String getCATEGORY_NAME() {
        return CATEGORY_NAME;
    }

    public void setCATEGORY_NAME(String CATEGORY_NAME) {
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

    public int getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(int CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getCATSTATUS() {
        return CATSTATUS;
    }

    public void setCATSTATUS(String CATSTATUS) {
        this.CATSTATUS = CATSTATUS;
    }

    public int getCATSTATUSID() {
        return CATSTATUSID;
    }

    public void setCATSTATUSID(int CATSTATUSID) {
        this.CATSTATUSID = CATSTATUSID;
    }
}
