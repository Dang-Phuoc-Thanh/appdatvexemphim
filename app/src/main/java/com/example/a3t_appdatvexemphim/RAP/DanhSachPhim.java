package com.example.a3t_appdatvexemphim.RAP;

import java.util.List;

public class DanhSachPhim {
    private Integer MaPhim;

    public DanhSachPhim() {
        // Default constructor required for calls to DataSnapshot.getValue(DanhSachPhim.class)
    }

   public Integer getMaPhim() {
        return MaPhim;
    }

    public void setMaPhim(Integer maPhim) {
        MaPhim = maPhim;
    }
}
