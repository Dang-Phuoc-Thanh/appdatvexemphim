package com.example.a3t_appdatvexemphim.RAP;

import java.io.Serializable;

public class Rap implements Serializable {
    private String name;
    private String address;
    private int imageResource;
    private double distance; // Thêm trường khoảng cách

    public Rap(String name, String address, int imageResource, double distance) {
        this.name = name;
        this.address = address;
        this.imageResource = imageResource;
        this.distance = distance; // Khởi tạo khoảng cách
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getImageResource() {
        return imageResource;
    }

    public double getDistance() {
        return distance; // Phương thức lấy khoảng cách
    }
}
