package com.example.a3t_appdatvexemphim;

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

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhuongThucThanhToan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhuongThucThanhToan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView back;
    private TextView zalo, momo, thenoidia, thequocte;

    public PhuongThucThanhToan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhuongThucThanhToan.
     */
    // TODO: Rename and change types and number of parameters
    public static PhuongThucThanhToan newInstance(String param1, String param2) {
        PhuongThucThanhToan fragment = new PhuongThucThanhToan();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phuong_thuc_thanh_toan, container, false);
        thenoidia = view.findViewById(R.id.thenoidia);
        thequocte = view.findViewById(R.id.thequocte);
        momo = view.findViewById(R.id.momo);
        zalo = view.findViewById(R.id.zalo);
        // Initialize the back ImageView
        back = view.findViewById(R.id.back); // Make sure this matches your ImageView's ID in the XML layout

        // Set an OnClickListener for the back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backpage();
            }
        });
        zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Đã chọn ZaloPay", Snackbar.LENGTH_LONG).show();
                backpage();
            }
        });
        momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Đã chọn MoMo", Snackbar.LENGTH_LONG).show();
                backpage();
            }
        });
        thequocte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Đã chọn Thẻ quốc tế", Snackbar.LENGTH_LONG).show();
                backpage();
            }
        });
        thenoidia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Đã chọn thẻ nội địa", Snackbar.LENGTH_LONG).show();
                backpage();
            }
        });

        // Return the inflated view
        return view;

    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}