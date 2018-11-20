package com.zeljko.earthquakes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quake")
public class Quake {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long quakeID;
    private double mag;
    private String date;
    private String time;
    private String place;
    private String url;

    public Quake() {
    }

    public Quake(double mag, String date, String time, String place, String url) {
        this.mag = mag;
        this.date = date;
        this.time = time;
        this.place = place;
        this.url = url;
    }

    public long getQuakeID() {
        return quakeID;
    }

    public void setQuakeID(long quakeID) {
        this.quakeID = quakeID;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Quake{" +
                "quakeID=" + quakeID +
                ", mag=" + mag +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
