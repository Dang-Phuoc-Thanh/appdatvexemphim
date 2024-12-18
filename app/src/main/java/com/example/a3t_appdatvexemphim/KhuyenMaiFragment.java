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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    private LinearLayout linear_nhap, linear_uudai;
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

        ArrayList<Voucher> voucherList = new ArrayList<>();
        VoucherAdapter adapter = new VoucherAdapter(getContext(), voucherList);
        listView.setAdapter(adapter);

        // Firebase Reference
        data = FirebaseDatabase.getInstance().getReference("KHUYENMAI");

        // Read data from Firebase
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voucherList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String title = dataSnapshot.child("ChuDe").getValue(String.class);
                    String discountAmountStr = dataSnapshot.child("Giam").getValue(String.class);
                    Long discountAmount = null;
                    if (discountAmountStr != null) {
                        try {
                            discountAmount = Long.parseLong(discountAmountStr);
                        } catch (NumberFormatException e) {
                            Log.e("ParsingError", "Failed to parse discount: " + discountAmountStr);
                        }
                    }
                    String description = dataSnapshot.child("NoiDung").getValue(String.class);
                    String imageUrl = dataSnapshot.child("HinhAnh").getValue(String.class);

                    if (title != null && discountAmount != null && description != null && imageUrl != null) {
                        voucherList.add(new Voucher(title, discountAmount, description, imageUrl));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error: " + error.getMessage());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Voucher selectedVoucher = voucherList.get(position);
            Bundle bundle = new Bundle();
            if (getArguments() != null) {
                if (getArguments().containsKey("selectedFilm")) {
                    bundle.putSerializable("selectedFilm", getArguments().getSerializable("selectedFilm"));
                }
                if (getArguments().containsKey("danhSachGheDuocChon")) {
                    bundle.putStringArrayList("danhSachGheDuocChon", getArguments().getStringArrayList("danhSachGheDuocChon"));
                }
                if (getArguments().containsKey("txtName")) {
                    bundle.putString("txtName", getArguments().getString("txtName"));
                }
                if (getArguments().containsKey("txtPhone")) {
                    bundle.putString("txtPhone", getArguments().getString("txtPhone"));
                }
                if (getArguments().containsKey("txtEmail")) {
                    bundle.putString("txtEmail", getArguments().getString("txtEmail"));
                }
                if (getArguments().containsKey("total")) {
                    bundle.putInt("total", getArguments().getInt("total"));
                }
            }
            bundle.putLong("DISCOUNT_AMOUNT", selectedVoucher.getDiscountAmount());

            ThanhToan thanhToanFragment = ThanhToan.newInstance(bundle);
            thanhToanFragment.setArguments(bundle);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, thanhToanFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        lichsu.setOnClickListener(view1 -> openFragment(new VoucherLichSu()));
        linear_uudai.setOnClickListener(view1 -> openFragment(new UuDai()));
        linear_nhap.setOnClickListener(view1 -> openFragment(new AddVoucher()));
        but_back.setOnClickListener(view1 -> backpage());
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
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
