package com.moh.departments.models;

public class MenuModel {

    public String menuName, url;
    public int menuid;
    public boolean hasChildren, isGroup;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, int menuid) {

        this.menuName = menuName;
        this.menuid = menuid;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getUrl() {
        return url;
    }

    public int getMenuid() {
        return menuid;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public boolean isGroup() {
        return isGroup;
    }
}