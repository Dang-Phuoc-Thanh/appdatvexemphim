package com.example.a3t_appdatvexemphim.danhgia;
public class BinhLuan {
    private String maBinhLuan;
    private String maPhim;
    private String id;
    private String nhanXet;
    private float danhGia;

    // Constructors, getters, and setters
    public BinhLuan() {}

    public BinhLuan(String maBinhLuan, String maPhim, String id, String nhanXet, float danhGia) {
        this.maBinhLuan = maBinhLuan;
        this.maPhim = maPhim;
        this.id = id;
        this.nhanXet = nhanXet;
        this.danhGia = danhGia;
    }

    public String getMaBinhLuan() { return maBinhLuan; }
    public void setMaBinhLuan(String maBinhLuan) { this.maBinhLuan = maBinhLuan; }

    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) { this.maPhim = maPhim; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNhanXet() { return nhanXet; }
    public void setNhanXet(String nhanXet) { this.nhanXet = nhanXet; }

    public float getDanhGia() { return danhGia; }
    public void setDanhGia(float danhGia) { this.danhGia = danhGia; }
}
