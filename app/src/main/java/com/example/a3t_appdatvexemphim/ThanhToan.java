package com.example.a3t_appdatvexemphim;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
        txt_thanhtien=view.findViewById(R.id.txt_thanhtien);
        txt_tongtien=view.findViewById(R.id.txt_tongtien);

        // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            float discountAmount = bundle.getFloat("DISCOUNT_AMOUNT", 0.0f); // Lấy discountAmount
            // Set giá trị giảm giá vào TextView, sử dụng String.format để hiển thị số một cách chính xác
            discountTextView.setText(String.format("%.2f", discountAmount)); // Hiển thị số giảm giá với 2 chữ số thập phân
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
                        Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        openFragment(new VeCuaToiFragment());
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
