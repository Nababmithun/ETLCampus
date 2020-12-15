package com.bipul.nrsingdidristict.model;

public class Stuff {
    private int stuffImage;
    private String name;
    private String designation;
    private String mobileNumber;
    private String mail;

    public Stuff(int stuffImage, String name, String designation, String mobileNumber, String mail) {
        this.stuffImage = stuffImage;
        this.name = name;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.mail = mail;
    }

    public int getStuffImage() {
        return stuffImage;
    }

    public void setStuffImage(int stuffImage) {
        this.stuffImage = stuffImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
