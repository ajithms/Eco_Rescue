package com.example.sample;

public class Complaint {

    String encode;
    String desc;
    double latitude;
    double longitude;
    public Complaint() {

    }

    public Complaint(String encode, String desc, double latitude, double longitude) {
        this.encode = encode;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




}
