package com.moh.departments.models;

public class CardviewDataModel {

    private String patname, docname, roomname, bedname, ptmrpid;
    private int patid, admcd;
    private String indate;

    public CardviewDataModel(String patname, String docname, String roomname, String bedname, String ptmrpid, int patid, int admcd, String indate) {
        this.patname = patname;
        this.docname = docname;
        this.roomname = roomname;
        this.bedname = bedname;
        this.patid = patid;
        this.admcd = admcd;
        this.indate = indate;
        this.ptmrpid = ptmrpid;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getBedname() {
        return bedname;
    }

    public void setBedname(String bedname) {
        this.bedname = bedname;
    }

    public int getPatid() {
        return patid;
    }

    public void setPatid(int patid) {
        this.patid = patid;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getPtmrpid() {
        return ptmrpid;
    }

    public void setPtmrpid(String ptmrpid) {
        this.ptmrpid = ptmrpid;
    }

    public int getAdmcd() {
        return admcd;
    }

    public void setAdmcd(int admcd) {
        this.admcd = admcd;
    }
}