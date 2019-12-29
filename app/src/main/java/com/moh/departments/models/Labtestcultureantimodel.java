package com.moh.departments.models;

public class Labtestcultureantimodel {
    String antibiotic, antiA, antiB, antiC;

    public Labtestcultureantimodel(String antibiotic, String antiA, String antiB, String antiC) {

        this.antibiotic = antibiotic;
        this.antiA = antiA;
        this.antiB = antiB;
        this.antiC = antiC;
    }

    public String getAntibiotic() {
        return antibiotic;
    }

    public void setAntibiotic(String antibiotic) {
        this.antibiotic = antibiotic;
    }

    public String getAntiA() {
        return antiA;
    }

    public void setAntiA(String antiA) {
        this.antiA = antiA;
    }

    public String getAntiB() {
        return antiB;
    }

    public void setAntiB(String antiB) {
        this.antiB = antiB;
    }

    public String getAntiC() {
        return antiC;
    }

    public void setAntiC(String antiC) {
        this.antiC = antiC;
    }
}
