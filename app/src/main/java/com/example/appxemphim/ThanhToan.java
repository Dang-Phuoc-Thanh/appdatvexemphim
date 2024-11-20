package com.example.appxemphim;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A Fragment class representing the "Thanh Toan" (Payment) screen.
 */
public class ThanhToan extends Fragment {

    private EditText txtPhone, txtName, txtEmail;
    private TextView txtPttt;
    private ImageView btnBack;
    private Button btnThanhToan;
    private EditText select_voucher;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ThanhToan() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment with provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThanhToan.
     */
    public static ThanhToan newInstance(String param1, String param2) {
        ThanhToan fragment = new ThanhToan();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        select_voucher=view.findViewById(R.id.select_voucher);
        // Initialize views
        txtPhone = view.findViewById(R.id.txt_phone);
        txtName = view.findViewById(R.id.txt_name);
        txtEmail = view.findViewById(R.id.txt_email);
        txtPttt = view.findViewById(R.id.select_pttt);
        btnBack = view.findViewById(R.id.back);
        btnThanhToan = view.findViewById(R.id.button_ThanhToan);

        // Set click listener for selecting payment method
        txtPttt.setOnClickListener(v -> openFragment(new PhuongThucThanhToan()));
        select_voucher.setOnClickListener(v -> openFragment(new VoucherFragment()));
        // Set click listener for the back button
        btnBack.setOnClickListener(v -> openFragment(new BapNuocFragment()));

        // Set click listener for the payment button
        btnThanhToan.setOnClickListener(v -> handleThanhToan());

        return view;
    }

    /**
     * Opens the specified fragment, replacing the current one.
     *
     * @param fragment The fragment to open.
     */
    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Handles the payment button click event.
     * Checks if required fields are filled, and if so, displays the payment confirmation dialog.
     */
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

    /**
     * Creates and returns a confirmation dialog for the payment process.
     *
     * @return AlertDialog instance.
     */
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
}
