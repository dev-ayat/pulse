package com.moh.departments.models;

import java.util.ArrayList;

public class MyInfo {
    String USER_CODE;
    String USER_NAME;
    String USER_ID;
    String USER_EMAIL;
    String USER_MOBILE;
    String USER_YEAR;
    String LAST_LOGIN;
    String LOGIN;
    ArrayList<MyPrivs> mListPrivs;

    int POS_MyPrivs;

    public MyInfo() {
        setPOS_MyPrivs(0);
        setLOGIN("0");
    }

    public int getPOS_MyPrivs() {
        return POS_MyPrivs;
    }

    public void setPOS_MyPrivs(int POS_MyPrivs) {
        this.POS_MyPrivs = POS_MyPrivs;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_MOBILE() {
        return USER_MOBILE;
    }

    public void setUSER_MOBILE(String USER_MOBILE) {
        this.USER_MOBILE = USER_MOBILE;
    }

    public String getUSER_YEAR() {
        return USER_YEAR;
    }

    public void setUSER_YEAR(String USER_YEAR) {
        this.USER_YEAR = USER_YEAR;
    }

    public String getLAST_LOGIN() {
        return LAST_LOGIN;
    }

    public void setLAST_LOGIN(String LAST_LOGIN) {
        this.LAST_LOGIN = LAST_LOGIN;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public ArrayList<MyPrivs> getmListPrivs() {
        return mListPrivs;
    }

    public void setmListPrivs(ArrayList<MyPrivs> mListPrivs) {
        this.mListPrivs = mListPrivs;
    }


}
