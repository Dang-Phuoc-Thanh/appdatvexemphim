package com.example.a3t_appdatvexemphim.XU;

public class Voucher_xu {
    private String title;  // Tiêu đề voucher
    private String brand;  // Thương hiệu
    private int coin;      // Số xu
    private int storeImageResId; // Resource ID của hình ảnh cửa hàng

    // Constructor để khởi tạo một đối tượng Voucher với hình ảnh cửa hàng
    public Voucher_xu(String title, String brand, int coin, int storeImageResId) {
        this.title = title;
        this.brand = brand;
        this.coin = coin;
        this.storeImageResId = storeImageResId;
    }

    // Getter cho thuộc tính title
    public String getTitle() {
        return title;
    }

    // Setter cho thuộc tính title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter cho thuộc tính brand
    public String getBrand() {
        return brand;
    }

    // Setter cho thuộc tính brand
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter cho thuộc tính coin
    public int getCoin() {
        return coin;
    }

    // Setter cho thuộc tính coin
    public void setCoin(int coin) {
        this.coin = coin;
    }

    // Getter cho thuộc tính storeImageResId
    public int getStoreImageResId() {
        return storeImageResId;
    }

    // Setter cho thuộc tính storeImageResId
    public void setStoreImageResId(int storeImageResId) {
        this.storeImageResId = storeImageResId;
    }
}
