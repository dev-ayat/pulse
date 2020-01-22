package com.moh.departments.activiteis;

public class slide {
    private int Image;
    private String Title;
    private String Details;

    public slide(int Image, String Title, String Details) {
        this.Image = Image;
        this.Title = Title;
        this.Details = Details;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}