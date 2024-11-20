package com.example.appxemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appxemphim.GiupDo.HoTroFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhacFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhacFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tk,hotro;
    private String mParam1;
    private String mParam2;
        private TextView rap;

    private ImageView vecuatoi;
    private ImageView bapnuoc;

    public KhacFragment() {
        // Required empty public constructor
    }

    public static KhacFragment newInstance(String param1, String param2) {
        KhacFragment fragment = new KhacFragment();
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

        View view = inflater.inflate(R.layout.fragment_khac, container, false);


        vecuatoi = view.findViewById(R.id.vecuatoi);
        bapnuoc = view.findViewById(R.id.bapnuoc);
        tk =view.findViewById(R.id.taikhoan);
        hotro=view.findViewById(R.id.hotro);
        vecuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new fragment instance
                Fragment newFragment = new VeCuaToiFragment(); // Replace with your target fragment

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();
            }
        });

        bapnuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new fragment instance
                Fragment newFragment = new BapNuocFragment(); // Replace with your target fragment

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();
            }
        });
        tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new TaiKhoan(); // Replace with your target fragment

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();

            }
        });

        hotro=view.findViewById(R.id.hotro);
        hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new HoTroFragment(); // Thay thế bằng HoTroFragment

                // Thay thế fragment hiện tại bằng HoTroFragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Đảm bảo R.id.frame_layout là ID của container layout
                fragmentTransaction.addToBackStack(null); // Tùy chọn: thêm vào back stack
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
