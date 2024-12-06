package com.example.a3t_appdatvexemphim;

public class KhuyenMai {
    private String chuDe;
    private String dieuKien;
    private String ngayBatDau;
    private String noiDung;
    private String urlHinh;


    // Constructor
    public KhuyenMai(String chuDe, String dieuKien, String ngayBatDau, String noiDung, String urlHinh) {
        this.chuDe = chuDe;
        this.dieuKien = dieuKien;
        this.ngayBatDau = ngayBatDau;
        this.noiDung = noiDung;
        this.urlHinh = urlHinh;
    }

    // Getters
    public String getChuDe() { return chuDe; }
    public String getDieuKien() { return dieuKien; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNoiDung() { return noiDung; }
    public String getUrlHinh() { return urlHinh; }
}

