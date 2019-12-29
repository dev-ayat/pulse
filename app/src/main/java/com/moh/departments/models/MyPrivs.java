package com.moh.departments.models;

import java.util.ArrayList;

public class MyPrivs {
    String LOC_CD;
    String LOC_TYPE;
    String LOC_TREE_NAME;
    ArrayList<Screen> mListScreen;

    public MyPrivs() {
    }

    public MyPrivs(String LOC_CD, String LOC_TYPE, String LOC_TREE_NAME, ArrayList<Screen> mListScreen) {
        this.LOC_CD = LOC_CD;
        this.LOC_TYPE = LOC_TYPE;
        this.LOC_TREE_NAME = LOC_TREE_NAME;
        this.mListScreen = mListScreen;
    }

    public String getLOC_CD() {
        return LOC_CD;
    }

    public void setLOC_CD(String LOC_CD) {
        this.LOC_CD = LOC_CD;
    }

    public String getLOC_TYPE() {
        return LOC_TYPE;
    }

    public void setLOC_TYPE(String LOC_TYPE) {
        this.LOC_TYPE = LOC_TYPE;
    }

    public String getLOC_TREE_NAME() {
        return LOC_TREE_NAME;
    }

    public void setLOC_TREE_NAME(String LOC_TREE_NAME) {
        this.LOC_TREE_NAME = LOC_TREE_NAME;
    }

    public ArrayList<Screen> getmListScreen() {
        return mListScreen;
    }

    public void setmListScreen(ArrayList<Screen> mListScreen) {
        this.mListScreen = mListScreen;
    }
}
