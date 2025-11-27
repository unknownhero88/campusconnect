package com.example.myapplication.LostANDFound;

public class rv_model_lost_found {
        String Type,Desc,cat,date_time,contact,img;
        public rv_model_lost_found(String type, String desc, String cat, String date_time, String contact, String img) {
            Type = type;
            Desc = desc;
            this.cat = cat;
            this.date_time = date_time;
            this.contact = contact;
            this.img = img;
        }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String data_time) {
        this.date_time = data_time;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
