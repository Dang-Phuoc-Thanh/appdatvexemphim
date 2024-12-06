package com.example.a3t_appdatvexemphim.DSphim;

import java.io.Serializable;

public class dsFILMHH implements Serializable {

    String name, time, day, noidung, datve, imageUrl, trailerURL;

    public dsFILMHH(String name, String time, String day, String noidung, String datve, String imageUrl, String trailerURL) {
        this.name = name;
        this.time = time;
        this.day = day;
        this.noidung = noidung;
        this.datve = datve;
        this.imageUrl = imageUrl;
        this.trailerURL = trailerURL;
    }

    public dsFILMHH() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getDatve() {
        return datve;
    }

    public void setDatve(String datve) {
        this.datve = datve;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }
}