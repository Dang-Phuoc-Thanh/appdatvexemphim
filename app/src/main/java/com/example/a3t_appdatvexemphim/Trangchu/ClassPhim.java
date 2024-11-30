package com.example.a3t_appdatvexemphim.Trangchu;

public class ClassPhim {
    public Integer MaPhim;
    public String TenPhim;
    public String NoiDung;
    public Integer ThoiLuong;
    public String DaoDien;
    public String DienVien;
    public Integer GioiHanTuoi;
    public Float DonGia;
    public String HinhAnh;
    public String Video;
    public Float DiemDanhGia;
    public String NgayKhoiChieu; // Thay đổi thành String
    public String NgayKetThuc;   // Thay đổi thành String
    public String QuocGia;
    public Integer TrangThai;

    public ClassPhim() {
    }

    public ClassPhim(Integer maPhim, Integer trangThai, String quocGia, String ngayKetThuc, String ngayKhoiChieu, Float diemDanhGia, String video, String hinhAnh, Float donGia, Integer gioiHanTuoi, String dienVien, String daoDien, Integer thoiLuong, String noiDung, String tenPhim) {
        MaPhim = maPhim;
        TrangThai = trangThai;
        QuocGia = quocGia;
        NgayKetThuc = ngayKetThuc;
        NgayKhoiChieu = ngayKhoiChieu;
        DiemDanhGia = diemDanhGia;
        Video = video;
        HinhAnh = hinhAnh;
        DonGia = donGia;
        GioiHanTuoi = gioiHanTuoi;
        DienVien = dienVien;
        DaoDien = daoDien;
        ThoiLuong = thoiLuong;
        NoiDung = noiDung;
        TenPhim = tenPhim;
    }
}
