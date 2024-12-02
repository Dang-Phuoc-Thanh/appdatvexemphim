package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.Voucher;
import com.example.a3t_appdatvexemphim.VoucherAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiFragment extends Fragment {
    ListView listView;
    List<Voucher> khuyenMaiList;
    VoucherAdapter adapter;
    DatabaseReference data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khuyen_mai, container, false);

        // Ánh xạ ListView
        listView = view.findViewById(R.id.lv_voucher);

        // Khởi tạo danh sách và adapter
        ArrayList<Voucher> voucherList = new ArrayList<>();
        VoucherAdapter adapter = new VoucherAdapter(getContext(), voucherList);
        listView.setAdapter(adapter);

        // Lấy tham chiếu Firebase
        data = FirebaseDatabase.getInstance().getReference("KHUYENMAI");

// Đọc dữ liệu từ Firebase
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voucherList.clear(); // Clear the previous list
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String title = dataSnapshot.child("ChuDe").getValue(String.class);
                    String discountAmountStr = dataSnapshot.child("Giam").getValue(String.class);
                    Long discountAmount = null;
                    if (discountAmountStr != null) {
                        try {
                            discountAmount = Long.parseLong(discountAmountStr);
                        } catch (NumberFormatException e) {
                            Log.e("ParsingError", "Failed to parse discount amount: " + discountAmountStr);
                        }
                    }
                    String description = dataSnapshot.child("NoiDung").getValue(String.class);
                    String imageUrl = dataSnapshot.child("HinhAnh").getValue(String.class);

                    if (title != null && discountAmount != null && description != null && imageUrl != null) {
                        voucherList.add(new Voucher(title, discountAmount, description, imageUrl));
                    }
                }
                adapter.notifyDataSetChanged(); // Update ListView
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error: " + error.getMessage());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            // Lấy mục được click
            Voucher selectedVoucher = voucherList.get(position);

            Long discountAmount = selectedVoucher.getDiscountAmount(); // Lấy discountAmount từ đối tượng Voucher

            // Tạo Fragment ThanhToan và truyền dữ liệu qua Bundle
            ThanhToan thanhToanFragment = ThanhToan.newInstance(discountAmount); // Truyền giá trị discountAmount

            // Chuyển sang Fragment ThanhToan
            openFragment(thanhToanFragment);
        });

        return view;
    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment); // Thay thế fragment trong container
        fragmentTransaction.addToBackStack(null); // Thêm vào back stack
        fragmentTransaction.commit(); // Hoàn thành giao dịch
    }

}
