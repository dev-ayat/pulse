package com.moh.departments.models;

import java.util.ArrayList;

public class PrivMenu {
    private int id;
    private String name;
    private ArrayList<Screen> screens;

    public PrivMenu() {
        screens = new ArrayList<>();
    }

    public PrivMenu(int id, String name, ArrayList<Screen> screens) {
        this.id = id;
        this.name = name;
        this.screens = screens;
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

    public ArrayList<Screen> getScreens() {
        return screens;
    }

    public void setScreens(ArrayList<Screen> screens) {
        this.screens = screens;
    }
}
