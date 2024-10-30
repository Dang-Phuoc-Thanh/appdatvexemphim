package com.example.a3t_appdatvexemphim;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class XUActivity extends AppCompatActivity {

    private RecyclerView recyclerViewVouchers;
    private VoucherAdapter voucherAdapter;
    private ArrayList<Voucher_xu> voucherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerViewVouchers = findViewById(R.id.recyclerViewVouchers);
        // Sử dụng GridLayoutManager với 2 cột
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewVouchers.setLayoutManager(gridLayoutManager);

        // Load dữ liệu voucher
        voucherList = new ArrayList<>();
        loadVoucherData();

        // Gắn adapter vào RecyclerView
        voucherAdapter = new VoucherAdapter(voucherList);
        recyclerViewVouchers.setAdapter(voucherAdapter);
    }

    private void loadVoucherData() {
        // Thêm các voucher với hình ảnh tương ứng
        voucherList.add(new Voucher_xu("Giảm 20K", "Cho đơn 80K", 35, R.drawable.logo));
        voucherList.add(new Voucher_xu("Giảm 20K", "Cho đơn 80K", 1, R.drawable.highlands));
        voucherList.add(new Voucher_xu("Giảm 20%", "Cho đơn 500K", 30, R.drawable.coopmart));
        voucherList.add(new Voucher_xu("Giảm 50%", "Cho đơn 1K", 130, R.drawable.oppo));
        voucherList.add(new Voucher_xu("Giảm 10K", "Cho đơn 100K", 330, R.drawable.pizza));
        voucherList.add(new Voucher_xu("Giảm 20%", "Cho đơn 80K", 20, R.drawable.xanhxm));
        voucherList.add(new Voucher_xu("Giảm 50%", "Cho đơn 500K", 30, R.drawable.coopmart));
        // Thêm các voucher khác vào danh sách...
    }

}