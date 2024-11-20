package com.example.appxemphim.XU;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView; // Nhập ImageView
import android.widget.Toast;

import com.example.appxemphim.R;
import com.example.appxemphim.TaiKhoan; // Nhập fragment TaiKhoan

import java.util.ArrayList;

public class XuFragment extends Fragment {

    private RecyclerView recyclerViewVouchers;
    private VoucherAdapter voucherAdapter;
    private ArrayList<Voucher_xu> voucherList;
    private ImageView backButton; // Khai báo ImageView cho nút quay lại

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho fragment này
        View view = inflater.inflate(R.layout.fragment_xu, container, false);

        recyclerViewVouchers = view.findViewById(R.id.recyclerViewVouchers);

        // Thiết lập RecyclerView với GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewVouchers.setLayoutManager(gridLayoutManager);

        // Tải dữ liệu voucher
        voucherList = new ArrayList<>();
        loadVoucherData();

        // Thiết lập adapter cho RecyclerView
        voucherAdapter = new VoucherAdapter(voucherList);
        recyclerViewVouchers.setAdapter(voucherAdapter);

        // Khởi tạo nút quay lại
        backButton = view.findViewById(R.id.ic_quaylai); // Đảm bảo ID này khớp với ID trong layout XML

        // Thiết lập OnClickListener cho nút quay lại
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thay thế XuFragment bằng fragment TaiKhoan
                Fragment taiKhoanFragment = new TaiKhoan(); // Tạo một instance của TaiKhoan
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, taiKhoanFragment); // Đảm bảo R.id.frame_layout khớp với ID của container
                fragmentTransaction.addToBackStack(null); // Tùy chọn: thêm vào back stack
                fragmentTransaction.commit();
            }
        });

        return view;

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
        // Thêm nhiều voucher khác nếu cần...
    }
}
