package com.moh.departments.models;


public class Labtestcultnotemodel {
    String gramstain, acidfaststain, KOH, Fungi, notes;

    public Labtestcultnotemodel(String gramstain, String acidfaststain, String KOH, String fungi, String notes) {
        this.gramstain = gramstain;
        this.acidfaststain = acidfaststain;
        this.KOH = KOH;
        Fungi = fungi;
        this.notes = notes;
    }

    public String getGramstain() {
        return gramstain;
    }

    public void setGramstain(String gramstain) {
        this.gramstain = gramstain;
    }

    public String getAcidfaststain() {
        return acidfaststain;
    }

    public void setAcidfaststain(String acidfaststain) {
        this.acidfaststain = acidfaststain;
    }

    public String getKOH() {
        return KOH;
    }

    public void setKOH(String KOH) {
        this.KOH = KOH;
    }

    public String getFungi() {
        return Fungi;
    }

    public void setFungi(String fungi) {
        Fungi = fungi;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
