package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class TaiKhoan extends Fragment {

    private static final String ARG_HOTEN = "hoten";
    private static final String ARG_GIOITINH = "GioiTinh";
    private static final String ARG_EMAIL = "Email";
    private static final String ARG_SDT = "SDT";

    private String hoten;
    private String GioiTinh;
    private String Email;
    private String SDT;

    private ImageView back;
    private ImageView xu, gioithieu;
    private TextView logout;

    public TaiKhoan() {
        // Required empty public constructor
    }

    public static TaiKhoan newInstance(String hoten, String GioiTinh, String Email, String SDT) {
        TaiKhoan fragment = new TaiKhoan();
        Bundle args = new Bundle();
        args.putString(ARG_HOTEN, hoten);
        args.putString(ARG_GIOITINH, GioiTinh);
        args.putString(ARG_EMAIL, Email);
        args.putString(ARG_SDT, SDT);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hoten = getArguments().getString(ARG_HOTEN);
            GioiTinh = getArguments().getString(ARG_GIOITINH);
            Email = getArguments().getString(ARG_EMAIL);
            SDT = getArguments().getString(ARG_SDT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        // Initialize views
        back = view.findViewById(R.id.buttonback);
        xu = view.findViewById(R.id.xu);
        logout = view.findViewById(R.id.logout);

        // Display the data
        TextView tvHoten = view.findViewById(R.id.tvHOTEN);
        TextView tvGioiTinh = view.findViewById(R.id.tvGIOITINH);
        TextView tvEmail = view.findViewById(R.id.tvEMAIL);
        TextView tvSdt = view.findViewById(R.id.tvSDT);
        // Add logs to check data
        Log.d("TaiKhoanFragment", "Hoten: " + hoten);
        Log.d("TaiKhoanFragment", "GioiTinh: " + GioiTinh);
        Log.d("TaiKhoanFragment", "Email: " + Email);
        Log.d("TaiKhoanFragment", "SDT: " + SDT);

        tvHoten.setText(hoten);
        tvGioiTinh.setText(GioiTinh);
        tvEmail.setText(Email);
        tvSdt.setText(SDT);

        // Set OnClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new KhacFragment(); // Replace with your target fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // Set OnClickListener for the xu ImageView
        xu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new XuFragment(); // Replace with your XuFragment class
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        gioithieu = view.findViewById(R.id.gioithieu);
        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hiện tại không khả dụng", Toast.LENGTH_LONG).show();
            }
        });

        // Set OnClickListener for the logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}