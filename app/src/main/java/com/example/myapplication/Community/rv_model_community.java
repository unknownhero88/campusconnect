package com.example.myapplication.Community;

public class rv_model_community {
    String desc,date,img;
    int rating;

    public rv_model_community(String desc, String date, String img, int rating) {
        this.desc = desc;
        this.date = date;
        this.img = img;
        this.rating = rating;
    }

    public rv_model_community(String desc, String date) {
        this.desc = desc;
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
