package com.example.a3t_appdatvexemphim.DSphim;

import java.io.Serializable;

public class dsFILMHH implements Serializable {



    String name, time, day, noidung, datve, imageUrl, trailerURL, maLich;
    String NgayKhoiChieu, TenPhim,NoiDung, DienVien, NgayKetThuc, QuocGia, HinhAnh, Video, DaoDien;
    double DiemDanhGia;
    int DonGia, GioiHanTuoi, MaPhim, ThoiLuong, TinhTrang;

    public dsFILMHH(String name, String time, String day, String noidung, String datve, String imageUrl, String trailerURL, String maLich) {
        this.name = name;
        this.time = time;
        this.day = day;
        this.noidung = noidung;
        this.datve = datve;
        this.imageUrl = imageUrl;
        this.trailerURL = trailerURL;
        this.maLich = maLich;

    }

    public dsFILMHH(String name, String time, String day, String datve, String imageUrl) {
        this.name = name;
        this.time = time;
        this.day = day;
        this.datve = datve;
        this.imageUrl = imageUrl;
    }

    public dsFILMHH() {
    }

    // Getters and setters for existing fields
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


    public String getDatve() {
        return datve;
    }

    public void setDatve(String datve) {
        this.datve = datve;
    }
    public String getNoidung() {
        return noidung;
    }
    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrailerUrl() {
        return trailerURL;
    }

    public void setTrailerUrl(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getMaLich() {
        return maLich;
    }

    public void setMaLich(String maLich) {
        this.maLich = maLich;
    }

    // Getters and setters for new fields
    public String getNgayKhoiChieu() {
        return NgayKhoiChieu;
    }

    public void setNgayKhoiChieu(String ngayKhoiChieu) {
        this.NgayKhoiChieu = ngayKhoiChieu;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.TenPhim = tenPhim;
    }

    public String getDienVien() {
        return DienVien;
    }

    public void setDienVien(String dienVien) {
        this.DienVien = dienVien;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.NgayKetThuc = ngayKetThuc;
    }



    public String getQuocGia() {
        return QuocGia;
    }

    public void setQuocGia(String quocGia) {
        this.QuocGia = quocGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.HinhAnh = hinhAnh;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        this.Video = video;
    }

    public String getDaoDien() {
        return DaoDien;
    }

    public void setDaoDien(String daoDien) {
        this.DaoDien = daoDien;
    }

    public double getDiemDanhGia() {
        return DiemDanhGia;
    }

    public void setDiemDanhGia(double diemDanhGia) {
        this.DiemDanhGia = diemDanhGia;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        this.DonGia = donGia;
    }

    public int getGioiHanTuoi() {
        return GioiHanTuoi;
    }

    public void setGioiHanTuoi(int gioiHanTuoi) {
        this.GioiHanTuoi = gioiHanTuoi;
    }

    public int getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(int maPhim) {
        this.MaPhim = maPhim;
    }

    public int getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.ThoiLuong = thoiLuong;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.TinhTrang = tinhTrang;
    }
}