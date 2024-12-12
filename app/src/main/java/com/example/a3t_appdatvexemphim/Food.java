package com.example.a3t_appdatvexemphim;

public class Food {
    private String TenDoAn;
    private String MoTa;
    private Long DonGia;
    private String HinhAnh;

    public Food() {}

    public Food(String tenDoAn, String moTa, Long donGia, String hinhAnh) {
        TenDoAn = tenDoAn;
        MoTa = moTa;

        DonGia = donGia;
        HinhAnh = hinhAnh;
    }

    public String getTenDoAn() { return TenDoAn; }
    public String getMoTa() { return MoTa; }
    public String getHinhAnh() { return HinhAnh; }
    public Long getDonGia() { return DonGia; }
}

