package com.moh.departments.models;

public class Departments {
    private String Deptname;
    private String Deptcd;

    public Departments() {

    }

    public Departments(String deptname, String deptcd) {
        Deptname = deptname;
        Deptcd = deptcd;
    }

    public String getDeptname() {
        return Deptname;
    }

    public void setDeptname(String deptname) {
        Deptname = deptname;
    }

    public String getDeptcd() {
        return Deptcd;
    }

    public void setDeptcd(String deptcd) {
        Deptcd = deptcd;
    }
}
