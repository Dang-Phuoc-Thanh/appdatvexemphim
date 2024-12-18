package com.example.a3t_appdatvexemphim.RAP;

public class DSLichChieu {
    private String MaLich;

    public DSLichChieu() {
        // Default constructor required for calls to DataSnapshot.getValue(DSLichChieu.class)
    }

    public DSLichChieu(String maLich) {
        MaLich = maLich;
    }

    public String getMaLich() {
        return MaLich;
    }

    public void setMaLich(String maLich) {
        MaLich = maLich;
    }
}