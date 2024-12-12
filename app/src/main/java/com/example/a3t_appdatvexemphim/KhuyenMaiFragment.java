package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView lichsu;
    private LinearLayout linear_nhap,linear_uudai;
    private ImageView but_back;
    List<Voucher> khuyenMaiList;
    VoucherAdapter adapter;
    DatabaseReference data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khuyen_mai, container, false);
        lichsu = view.findViewById(R.id.lichsu);
        but_back = view.findViewById(R.id.but_back);

        listView = view.findViewById(R.id.lv_voucher);
        linear_nhap = view.findViewById(R.id.linear_nhap);
        linear_uudai = view.findViewById(R.id.linearuudai);
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
            Bundle bundle= new Bundle();
            if (getArguments() != null) {
                if (getArguments().containsKey("selectedFilm")) {
                    bundle.putSerializable("selectedFilm", getArguments().getSerializable("selectedFilm"));
                }
                if (getArguments().containsKey("danhSachGheDuocChon")) {
                    bundle.putStringArrayList("danhSachGheDuocChon", getArguments().getStringArrayList("danhSachGheDuocChon"));
                }
                if (getArguments().containsKey("txtName")) {
                    bundle.putString("txtName",getArguments().getString("txtName"));

                }
                if (getArguments().containsKey("txtPhone")) {
                    bundle.putString("txtPhone",getArguments().getString("txtPhone"));

                }
                if (getArguments().containsKey("txtEmail")) {
                    bundle.putString("txtEmail",getArguments().getString("txtEmail"));

                }
            }
            Long discountAmount = selectedVoucher.getDiscountAmount(); // Lấy discountAmount từ đối tượng Voucher
            bundle.putFloat("discountAmount",discountAmount);
            // Tạo Fragment ThanhToan và truyền dữ liệu qua Bundle
            ThanhToan thanhToanFragment = ThanhToan.newInstance(bundle); // Truyền giá trị discountAmount

            thanhToanFragment.setArguments(bundle); // Pass bundle to ThanhToanFragment
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, thanhToanFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            // Chuyển sang Fragment ThanhToan



        });
        lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoucherLichSu voucherLichSuFragment = new VoucherLichSu(); // Tạo instance của VoucherLichSu
                openFragment(voucherLichSuFragment);
            }
        });
        linear_uudai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UuDai uuDai = new UuDai(); // Tạo instance của VoucherLichSu
                openFragment(uuDai);
            }
        });
        linear_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddVoucher addVoucher = new AddVoucher(); // Tạo instance của VoucherLichSu
                openFragment(addVoucher);
            }
        });
        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backpage();
            }
        });

        return view;
    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (fragment != null) {
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            Log.e("openFragment", "Fragment truyền vào null.");
        }
    }
}
