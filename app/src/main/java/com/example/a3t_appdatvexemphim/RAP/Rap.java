package com.example.a3t_appdatvexemphim.RAP;

import java.io.Serializable;

public class Rap implements Serializable {
    private String DiaChi;
    private String HinhAnh;
    private String KhoangCach;
    private String MaTP;
    private String TenRap;
    private int TrangThai;

    // Default constructor required for calls to DataSnapshot.getValue(Rap.class)
    public Rap() {
    }

    public Rap(String diaChi, String hinhAnh, String khoangCach, String maTP, String tenRap, int trangThai) {
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        KhoangCach = khoangCach;
        MaTP = maTP;
        TenRap = tenRap;
        TrangThai = trangThai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public String getKhoangCach() {
        return KhoangCach;
    }

    public String getMaTP() {
        return MaTP;
    }

    public String getTenRap() {
        return TenRap;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

}