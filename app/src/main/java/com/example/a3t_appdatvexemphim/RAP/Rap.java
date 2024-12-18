package com.example.a3t_appdatvexemphim.RAP;

import java.io.Serializable;
import java.util.List;

public class Rap implements Serializable {
    private String DiaChi;
    private String HinhAnh;
    private String KhoangCach;
    private String MaTP;
    private String TenRap;
    private String MaRap;
    private int TrangThai;
    private String MaLich;


    // Default constructor required for calls to DataSnapshot.getValue(Rap.class)
    public Rap() {
    }

    public Rap(String diaChi, String hinhAnh, String khoangCach, String maTP, String tenRap, String maRap, int trangThai, String maLich) {
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        KhoangCach = khoangCach;
        MaTP = maTP;
        TenRap = tenRap;
        MaRap = maRap;
        TrangThai = trangThai;
        MaLich = maLich;

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

    public String getMaRap() {
        return MaRap;
    }

    public int getTrangThai() {
        return TrangThai;
    }


    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public void setKhoangCach(String khoangCach) {
        KhoangCach = khoangCach;
    }

    public void setMaTP(String maTP) {
        MaTP = maTP;
    }

    public void setTenRap(String tenRap) {
        TenRap = tenRap;
    }

    public void setMaRap(String maRap) {
        MaRap = maRap;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
    public String getMaLich() {
        return MaLich;
    }
    public void setMaLich(String maLich) {
        MaLich = maLich;
    }



}