package com.zeljko.earthquakes.entity;

public class Data {

    private double  mag;
    private String place;
    private long time;
    private String url;

    public Data(double mag, String place, long time, String url) {
        this.mag = mag;
        this.place = place;
        this.time = time;
        this.url = url;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }
}