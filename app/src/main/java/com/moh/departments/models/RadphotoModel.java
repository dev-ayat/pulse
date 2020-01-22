package com.moh.departments.models;

public class RadphotoModel {
    private String ORDERD_RESULT_DATE;
    private String SERVICE_NAME_AR;
    private String ORGAN_SERVICE_CD;
    private String ORGAN_NAME_AR;
    private String ORGAN_CODE;
    private String MRP_ID;

    public RadphotoModel(String ORDERD_RESULT_DATE, String SERVICE_NAME_AR,
                         String ORGAN_SERVICE_CD, String ORGAN_NAME_AR, String ORGAN_CODE, String MRP_ID) {
        this.ORDERD_RESULT_DATE = ORDERD_RESULT_DATE;
        this.SERVICE_NAME_AR = SERVICE_NAME_AR;
        this.ORGAN_SERVICE_CD = ORGAN_SERVICE_CD;
        this.ORGAN_NAME_AR = ORGAN_NAME_AR;
        this.ORGAN_CODE = ORGAN_CODE;
        this.MRP_ID = MRP_ID;
    }

    public String getMRP_ID() {
        return MRP_ID;
    }

    public void setMRP_ID(String MRP_ID) {
        this.MRP_ID = MRP_ID;
    }

    public String getORDERD_RESULT_DATE() {
        return ORDERD_RESULT_DATE;
    }

    public void setORDERD_RESULT_DATE(String ORDERD_RESULT_DATE) {
        this.ORDERD_RESULT_DATE = ORDERD_RESULT_DATE;
    }

    public String getSERVICE_NAME_AR() {
        return SERVICE_NAME_AR;
    }

    public void setSERVICE_NAME_AR(String SERVICE_NAME_AR) {
        this.SERVICE_NAME_AR = SERVICE_NAME_AR;
    }

    public String getORGAN_SERVICE_CD() {
        return ORGAN_SERVICE_CD;
    }

    public void setORGAN_SERVICE_CD(String ORGAN_SERVICE_CD) {
        this.ORGAN_SERVICE_CD = ORGAN_SERVICE_CD;
    }

    public String getORGAN_NAME_AR() {
        return ORGAN_NAME_AR;
    }

    public void setORGAN_NAME_AR(String ORGAN_NAME_AR) {
        this.ORGAN_NAME_AR = ORGAN_NAME_AR;
    }

    public String getORGAN_CODE() {
        return ORGAN_CODE;
    }

    public void setORGAN_CODE(String ORGAN_CODE) {
        this.ORGAN_CODE = ORGAN_CODE;
    }
}
