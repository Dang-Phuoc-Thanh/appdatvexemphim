package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.XU.XuFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaiKhoan extends Fragment {

    private static final String ARG_USER_ID = "userId";
    private String userId;
    private String hoten;
    private String gioiTinh;
    private String email;
    private String sdt;

    private ImageView back, xu, gioithieu;
    private TextView logout, hotenTextView, gioiTinhTextView, emailTextView, sdtTextView;

    public TaiKhoan() {
        // Required empty public constructor
    }

    public static TaiKhoan newInstance(String userId) {
        TaiKhoan fragment = new TaiKhoan();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        // Ánh xạ các view
        back = view.findViewById(R.id.buttonback);
        xu = view.findViewById(R.id.xu);
        gioithieu = view.findViewById(R.id.gioithieu);
        logout = view.findViewById(R.id.logout);

        hotenTextView = view.findViewById(R.id.tvHOTEN);
        gioiTinhTextView = view.findViewById(R.id.tvGIOITINH);
        emailTextView = view.findViewById(R.id.tvEMAIL);
        sdtTextView = view.findViewById(R.id.tvSDT);

        // Xử lý nút quay lại
        back.setOnClickListener(v -> {
            Fragment newFragment = new KhacFragment(); // Thay bằng fragment khác nếu cần
            replaceFragment(newFragment);
        });

        // Xử lý nút xu
        xu.setOnClickListener(v -> {
            Fragment newFragment = new XuFragment();
            replaceFragment(newFragment);
        });

        // Xử lý nút giới thiệu
        gioithieu.setOnClickListener(v -> Toast.makeText(getContext(), "Hiện tại không khả dụng", Toast.LENGTH_LONG).show());

        // Xử lý đăng xuất
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });

        // Tải thông tin người dùng từ Firebase
        loadUserInfo();

        return view;
    }

    private void loadUserInfo() {
        if (userId == null || userId.isEmpty()) {
            Toast.makeText(getActivity(), "Không tìm thấy thông tin người dùng.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TAIKHOAN").child(userId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Lấy dữ liệu từ Firebase
                    hoten = dataSnapshot.child("hoten").getValue(String.class);
                    gioiTinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    email = dataSnapshot.child("email").getValue(String.class);
                    sdt = dataSnapshot.child("sdt").getValue(String.class);

                    // Cập nhật giao diện
                    updateUI();
                } else {
                    Toast.makeText(getActivity(), "Tài khoản không tồn tại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Lỗi khi tải dữ liệu người dùng.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        // Kiểm tra và cập nhật các TextView
        if (hoten != null) hotenTextView.setText(hoten);
        if (gioiTinh != null) gioiTinhTextView.setText(gioiTinh);
        if (email != null) emailTextView.setText(email);
        if (sdt != null) sdtTextView.setText(sdt);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
