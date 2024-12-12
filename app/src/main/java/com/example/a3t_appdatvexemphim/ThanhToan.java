package com.example.a3t_appdatvexemphim;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A Fragment class representing the "Thanh Toan" (Payment) screen.
 */
public class ThanhToan extends Fragment {

    private EditText txtPhone, txtName, txtEmail;
    private TextView txtPttt,discountTextView,txt_tongtien,txt_thanhtien;
    private ImageView btnBack;
    private Button btnThanhToan;
    private EditText select_voucher;

    public ThanhToan() {
        // Required empty public constructor
    }

    public static ThanhToan newInstance(float discountAmount) {
        ThanhToan fragment = new ThanhToan();
        Bundle args = new Bundle();
        args.putFloat("DISCOUNT_AMOUNT", discountAmount); // Truyền giá trị giảm giá
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        // Ánh xạ các view
        select_voucher = view.findViewById(R.id.select_voucher);
        txtPhone = view.findViewById(R.id.txt_phone);
        txtName = view.findViewById(R.id.txt_name);
        txtEmail = view.findViewById(R.id.txt_email);
        txtPttt = view.findViewById(R.id.select_pttt);
        btnBack = view.findViewById(R.id.back);
        btnThanhToan = view.findViewById(R.id.button_ThanhToan);
        discountTextView = view.findViewById(R.id.txt_giamgia);
        txt_thanhtien = view.findViewById(R.id.txt_thanhtien);
        txt_tongtien = view.findViewById(R.id.txt_tongtien);

        // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();
        float discountAmount = 0;
        if (bundle != null) {
            discountAmount = bundle.getFloat("DISCOUNT_AMOUNT", 0.0f);
            // Set giá trị giảm giá vào TextView, sử dụng String.format để hiển thị số một cách chính xác
            discountTextView.setText(String.format("%.0f" + " Vnd", discountAmount));
            txt_thanhtien.setText("90000 Vnd");

            txt_tongtien.setText(String.format("%.0f" + " VND", 90000 - discountAmount));// Hiển thị số giảm giá với 2 chữ số thập phân
        } else {
            txt_tongtien.setText("90000 Vnd");
            txt_thanhtien.setText("90000 Vnd");
            discountTextView.setText("0 Vnd");
        }

        // Set click listener
        txtPttt.setOnClickListener(v -> openFragment(new PhuongThucThanhToan()));
        select_voucher.setOnClickListener(v -> openFragment(new KhuyenMaiFragment()));
        btnBack.setOnClickListener(v -> backpage());
        btnThanhToan.setOnClickListener(v -> handleThanhToan());

        return view;
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void handleThanhToan() {
        if (txtName.getText().toString().isEmpty() ||
                txtPhone.getText().toString().isEmpty() ||
                txtEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
        } else {
            Dialog dialog = createDialog();
            dialog.show();
        }
    }

    AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn có muốn thanh toán")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show success message
                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();

                        // Create a new Bundle to pass selected film and seats
                        Bundle bundle = new Bundle();

                        // Assuming these values are passed to the fragment beforehand
                        if (getArguments() != null) {

                            // Get selected film and seats if available
                            if (getArguments().containsKey("selectedFilm")) {
                                bundle.putSerializable("selectedFilm", getArguments().getSerializable("selectedFilm"));

                            }
                            if (getArguments().containsKey("danhSachGheDuocChon")) {
                                bundle.putStringArrayList("danhSachGheDuocChon", getArguments().getStringArrayList("danhSachGheDuocChon"));
                            }
                        }

                        // Pass the bundle to the VeCuaToiFragment
                        VeCuaToiFragment veCuaToiFragment = VeCuaToiFragment.newInstance(bundle);

                        // Start the VeCuaToiFragment with the passed data
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, veCuaToiFragment); // Replace with VeCuaToiFragment
                        transaction.addToBackStack(null); // Allow back navigation
                        transaction.commit();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }


}
