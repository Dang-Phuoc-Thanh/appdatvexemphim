package com.example.a3t_appdatvexemphim;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

/**
 * A Fragment class representing the "Thanh Toan" (Payment) screen.
 */
public class ThanhToan extends Fragment {

    private EditText txtPhone, txtName, txtEmail;

    private TextView txtPttt, discountTextView, txt_tongtien, txtgiave,txtdoan;

    private ImageView btnBack;
    private Button btnThanhToan;
    private EditText select_voucher;

    public ThanhToan() {
        // Required empty public constructor
    }

    public static ThanhToan newInstance(Bundle bundle) {
        ThanhToan fragment = new ThanhToan();
        fragment.setArguments(bundle);
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
        txtgiave = view.findViewById(R.id.txt_giave);
        txt_tongtien = view.findViewById(R.id.txt_tongtien);
        txtdoan=view.findViewById(R.id.txtdoan);

        // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();


        int giaMoiGhe = 90000;
        int giaBapNuoc = 0;

        if (bundle != null) {
            Long discountAmount = bundle.getLong("DISCOUNT_AMOUNT", 0L);

            if (discountAmount == null || discountAmount == 0L) {
                discountTextView.setText("0 VND");
                ArrayList<String> danhSachGheDuocChon = bundle.getStringArrayList("danhSachGheDuocChon");
                int soLuongGhe = (danhSachGheDuocChon != null) ? danhSachGheDuocChon.size() : 0;

                Integer doan=bundle.getInt("total");

                txtdoan.setText(String.format("%d VND",doan));
                txtgiave.setText(String.format("%d VND",soLuongGhe*giaMoiGhe));

                txt_tongtien.setText(String.format("%d VND",doan+(soLuongGhe*giaMoiGhe)));
            }
            else {
                discountTextView.setText(String.format("-%d VND", discountAmount));



                // Lấy danh sách ghế đã chọn từ bundle
                ArrayList<String> danhSachGheDuocChon = bundle.getStringArrayList("danhSachGheDuocChon");
                int soLuongGhe = (danhSachGheDuocChon != null) ? danhSachGheDuocChon.size() : 0;

                // Tính tổng tiền ghế
                int tongTienGhe = soLuongGhe * giaMoiGhe;

                // Lấy giá bắp nước từ bundle
                giaBapNuoc = bundle.getInt("total");
                txtdoan.setText(String.format("%d VND",giaBapNuoc));
                // Tính tổng tiền
                Long tongTien = tongTienGhe + giaBapNuoc - discountAmount;

                // Hiển thị tổng tiền và các thông tin khác
                txtgiave.setText(String.format("%d VND",soLuongGhe*giaMoiGhe));
                String name = bundle.getString("txtName");
                String email = bundle.getString("txtEmail");
                String phone = bundle.getString("txtPhone");
                txtPhone.setText(phone);
                txtName.setText(name);
                txtEmail.setText(email);
                txt_tongtien.setText(String.format("%d VND", tongTien));
            }

            }



        // Set click listener
        txtPttt.setOnClickListener(v -> openFragment(new PhuongThucThanhToan()));
        select_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();

                // Truyền thông tin từ ThanhToan sang KhuyenMaiFragment
                bundle1.putString("txtName", txtName.getText().toString());
                bundle1.putString("txtPhone", txtPhone.getText().toString());
                bundle1.putString("txtEmail", txtEmail.getText().toString());

                if (getArguments() != null) {
                    if (getArguments().containsKey("selectedFilm")) {
                        bundle1.putSerializable("selectedFilm", getArguments().getSerializable("selectedFilm"));
                    }
                    if (getArguments().containsKey("danhSachGheDuocChon")) {
                        bundle1.putStringArrayList("danhSachGheDuocChon", getArguments().getStringArrayList("danhSachGheDuocChon"));
                    }
                    if (getArguments().containsKey("total")) {
                        bundle1.putInt("total", getArguments().getInt("total"));
                    }

                }

                // Mở KhuyenMaiFragment và truyền bundle1
                KhuyenMaiFragment khuyenMaiFragment = new KhuyenMaiFragment();
                khuyenMaiFragment.setArguments(bundle1);
                openFragment(khuyenMaiFragment);
            }
        });
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
