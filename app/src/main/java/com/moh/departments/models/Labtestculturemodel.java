package com.moh.departments.models;

import java.util.ArrayList;

public class Labtestculturemodel {
    String culturname, cultureres, orgA, orgAcount, orgB, orgBcount, orgC, orgCcount, statcultcd;
    private ArrayList<Object> mantiList;

    public Labtestculturemodel(String culturname, String cultureres, String orgA, String orgAcount, String orgB, String orgBcount, String orgC,
                               String orgCcount, String statcultcd) {
        this.culturname = culturname;
        this.cultureres = cultureres;
        this.orgA = orgA;
        this.orgAcount = orgAcount;
        this.orgB = orgB;
        this.orgBcount = orgBcount;
        this.orgC = orgC;
        this.orgCcount = orgCcount;
        this.statcultcd = statcultcd;
        mantiList = new ArrayList<>();

    }

    public String getCulturname() {
        return culturname;
    }

    public void setCulturname(String culturname) {
        this.culturname = culturname;
    }

    public String getCultureres() {
        return cultureres;
    }

    public void setCultureres(String cultureres) {
        this.cultureres = cultureres;
    }

    public String getOrgA() {
        return orgA;
    }

    public void setOrgA(String orgA) {
        this.orgA = orgA;
    }

    public String getOrgAcount() {
        return orgAcount;
    }

    public void setOrgAcount(String orgAcount) {
        this.orgAcount = orgAcount;
    }

    public String getOrgB() {
        return orgB;
    }

    public void setOrgB(String orgB) {
        this.orgB = orgB;
    }

    public String getOrgBcount() {
        return orgBcount;
    }

    public void setOrgBcount(String orgBcount) {
        this.orgBcount = orgBcount;
    }

    public String getOrgC() {
        return orgC;
    }

    public void setOrgC(String orgC) {
        this.orgC = orgC;
    }

    public String getOrgCcount() {
        return orgCcount;
    }

    public void setOrgCcount(String orgCcount) {
        this.orgCcount = orgCcount;
    }

    public String getStatcultcd() {
        return statcultcd;
    }

    public void setStatcultcd(String statcultcd) {
        this.statcultcd = statcultcd;
    }

    public ArrayList<Object> getMantiList() {
        return mantiList;
    }

    public void setMantiList(ArrayList<Object> mantiList) {
        this.mantiList = mantiList;
    }
}
