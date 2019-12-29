package com.moh.departments.models;

import java.io.Serializable;
import java.util.ArrayList;

public class LaborderModel implements Serializable {
    private String GROUP_NAME;
    private String GROUP_CD;
    private ArrayList<Labordertestmodel> mListCat;

    public LaborderModel(String GROUP_NAME, String GROUP_CD) {
        this.GROUP_NAME = GROUP_NAME;
        this.GROUP_CD = GROUP_CD;
        mListCat = new ArrayList<>();
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public String getGROUPCD() {
        return GROUP_CD;
    }

    public void setGROUPCD(String GROUPCD) {
        this.GROUP_CD = GROUP_CD;
    }

    public ArrayList<Labordertestmodel> getmListCat() {
        return mListCat;
    }

    public void setmListCat(ArrayList<Labordertestmodel> mListCat) {
        this.mListCat = mListCat;
    }
}
