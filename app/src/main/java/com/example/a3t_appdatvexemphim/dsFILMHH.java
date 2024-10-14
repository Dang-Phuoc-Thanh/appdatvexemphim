package com.example.a3t_appdatvexemphim;

import java.io.Serializable;

public class dsFILMHH implements Serializable {
    String  name, time, day, datve;
   int imageID;

    public dsFILMHH(String name, String time, String day, String datve, int imageID) {
        this.name = name;
        this.time = time;
        this.day = day;
        this.datve = datve;
        this.imageID = imageID;
    }

}
