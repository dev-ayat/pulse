package com.moh.departments.models;

public class EfileModel {
    private String VISTE_DATE;
    private String HOSSID;
    private String IDNUMBER;

    public EfileModel(String VISTE_DATE, String HOSSID, String IDNUMBER) {
        this.VISTE_DATE = VISTE_DATE;
        this.HOSSID = HOSSID;
        this.IDNUMBER = IDNUMBER;
    }

    public String getVISTE_DATE() {
        return VISTE_DATE;
    }

    public void setVISTE_DATE(String VISTE_DATE) {
        this.VISTE_DATE = VISTE_DATE;
    }

    public String getHOSSID() {
        return HOSSID;
    }

    public void setHOSSID(String HOSSID) {
        this.HOSSID = HOSSID;
    }

    public String getIDNUMBER() {
        return IDNUMBER;
    }

    public void setIDNUMBER(String IDNUMBER) {
        this.IDNUMBER = IDNUMBER;
    }
}
