package com.moh.departments.models;

/**
 * Created by iyad on 05/12/2017.
 */

public class Screen {
    int id;
    String name;

    public Screen() {

    }

    public Screen(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
