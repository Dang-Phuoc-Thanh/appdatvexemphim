package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.GiupDo.HoTroFragment;
import com.example.a3t_appdatvexemphim.BapNuocFragment;
import com.example.a3t_appdatvexemphim.VeCuaToiFragment;
import com.example.a3t_appdatvexemphim.TaiKhoan;

public class KhacFragment extends Fragment {

    private static final String ARG_USER_ID = "USER_ID"; // Key để truyền userId qua arguments
    private String userId; // Biến lưu userId

    private TextView tk, hotro;
    private ImageView vecuatoi, bapnuoc;

    public KhacFragment() {
        // Required empty public constructor
    }

    // Phương thức factory để tạo instance mới của KhacFragment
    public static KhacFragment newInstance(String userId) {
        KhacFragment fragment = new KhacFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lấy userId từ arguments
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho KhacFragment
        View view = inflater.inflate(R.layout.fragment_khac, container, false);

        // Ánh xạ các view
        vecuatoi = view.findViewById(R.id.vecuatoi);
        bapnuoc = view.findViewById(R.id.bapnuoc);
        tk = view.findViewById(R.id.taikhoan);
        hotro = view.findViewById(R.id.hotro);

        // Xử lý sự kiện "Vé của tôi"
        vecuatoi.setOnClickListener(v -> {
            Fragment newFragment = new ThanhVienFragment();
            replaceFragment(newFragment);
        });

        // Xử lý sự kiện "Bắp nước"
        bapnuoc.setOnClickListener(v -> {
            Fragment newFragment = new DoAnFragment();
            replaceFragment(newFragment);
        });

        // Xử lý sự kiện "Tài khoản" (truyền userId)
        tk.setOnClickListener(v -> {
            Fragment newFragment = TaiKhoan.newInstance(userId); // Truyền userId tới TaiKhoanFragment
            replaceFragment(newFragment);
        });

        // Xử lý sự kiện "Hỗ trợ"
        hotro.setOnClickListener(v -> {
            Fragment newFragment = new HoTroFragment();
            replaceFragment(newFragment);
        });

        return view;
    }

    // Hàm thay thế fragment trong container
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment); // frame_layout là container
        fragmentTransaction.addToBackStack(null); // Thêm vào back stack
        fragmentTransaction.commit();
    }
}
