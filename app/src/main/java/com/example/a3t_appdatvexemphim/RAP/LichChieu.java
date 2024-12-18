package com.example.a3t_appdatvexemphim.RAP;

import java.util.List;

public class LichChieu {
    private String MaLich;
    private String NgayChieu;
    private String Thu;



    public LichChieu() {
        // Default constructor required for calls to DataSnapshot.getValue(LichChieu.class)
    }

    public LichChieu(String maLich, String ngayChieu, String thu) {
        MaLich = maLich;
        NgayChieu = ngayChieu;
        Thu = thu;


    }


    public String getMaLich() {
        return MaLich;
    }

    public void setMaLich(String maLich) {
        MaLich = maLich;
    }

    public String getNgayChieu() {
        return NgayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        NgayChieu = ngayChieu;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }


}