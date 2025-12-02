package com.example.myapplication.LostANDFound;

public class LostFound_Model {
    private long id;
    private Character type;
    private String desc;
    private String cat;
    private String dateTime;
    private String contact;
    private String image;
    private int UID;
    private String status;

    public LostFound_Model(long id, Character type, String desc, String cat, String dateTime, String contact, String image, int UID, String status) {
        this.id = id;
        this.type = type;
        this.desc = desc;
        this.cat = cat;
        this.dateTime = dateTime;
        this.contact = contact;
        this.image = image;
        this.UID = UID;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Character getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getCat() {
        return cat;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getContact() {
        return contact;
    }

    public String getImage() {
        return image;
    }

    public int getUID() {
        return UID;
    }

    public String getStatus() {
        return status;
    }
}
