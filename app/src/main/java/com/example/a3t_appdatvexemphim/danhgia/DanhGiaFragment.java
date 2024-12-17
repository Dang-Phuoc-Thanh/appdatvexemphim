package com.example.a3t_appdatvexemphim.danhgia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.VeCuaToiFragment;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhGiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhGiaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button gui;
    private ImageView back;
    private ImageView hinh_anh;
    private TextView ten_phim;
    public DanhGiaFragment() {
        // Required empty public constructor
    }

    public static DanhGiaFragment newInstance(String param1, String param2) {
        DanhGiaFragment fragment = new DanhGiaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhgia, container, false);
        hinh_anh = view.findViewById(R.id.hinh_anh);
        ten_phim = view.findViewById(R.id.ten_phim);
        gui = view.findViewById(R.id.gui);
        if (getArguments() != null) {
            String tenPhim = getArguments().getString("TenPhim1");
            String hinhAnh = getArguments().getString("HinhAnh1");

            // Hiển thị dữ liệu lên giao diện
            ten_phim.setText(tenPhim);
            Glide.with(this).load(hinhAnh).into(hinh_anh);
        } else {
            Toast.makeText(getContext(), "chưa gọi được Arguments", Toast.LENGTH_SHORT).show();
        }

        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Đánh giá thành công", Snackbar.LENGTH_LONG).show();
                backpage();
            }
        });
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
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
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}
