package com.moh.departments.models;

public class LabTestDataModel {
    private String txttestname, lbtestunit;
    private String txttestvalue, lbtestminref, lbtestmaxref;

    public LabTestDataModel(String txttestname, String lbtestunit, String txttestvalue, String lbtestminref, String lbtestmaxref) {
        this.txttestname = txttestname;
        this.lbtestunit = lbtestunit;
        this.txttestvalue = txttestvalue;
        this.lbtestminref = lbtestminref;
        this.lbtestmaxref = lbtestmaxref;
    }


    public String getTxttestname() {
        return txttestname;
    }

    public void setTxttestname(String txttestname) {
        this.txttestname = txttestname;
    }

    public String getLbtestunit() {
        return lbtestunit;
    }

    public void setLbtestunit(String lbtestunit) {
        this.lbtestunit = lbtestunit;
    }

    public String getTxttestvalue() {
        return txttestvalue;
    }

    public void setTxttestvalue(String txttestvalue) {
        this.txttestvalue = txttestvalue;
    }

    public String getLbtestminref() {
        return lbtestminref;
    }

    public void setLbtestminref(String lbtestminref) {
        this.lbtestminref = lbtestminref;
    }

    public String getLbtestmaxref() {
        return lbtestmaxref;
    }

    public void setLbtestmaxref(String lbtestmaxref) {
        this.lbtestmaxref = lbtestmaxref;
    }
}
