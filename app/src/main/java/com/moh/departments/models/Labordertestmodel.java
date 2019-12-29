package com.moh.departments.models;

import java.io.Serializable;

public class Labordertestmodel implements Serializable {
    private String TEST_NAME;
    private String TEST_CD;
    private boolean isChecked;
    private boolean isAddedtofav;


    public Labordertestmodel(String TEST_NAME, String TEST_CD) {
        this.TEST_NAME = TEST_NAME;
        this.TEST_CD = TEST_CD;
    }

    public String getTEST_NAME() {
        return TEST_NAME;
    }

    public void setTEST_NAME(String TEST_NAME) {
        this.TEST_NAME = TEST_NAME;
    }

    public String getTEST_CD() {
        return TEST_CD;
    }

    public void setTEST_CD(String TEST_CD) {
        this.TEST_CD = TEST_CD;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isAddedtofav() {
        return isAddedtofav;
    }

    public void setAddedtofav(boolean addedtofav) {
        isAddedtofav = addedtofav;
    }


}
