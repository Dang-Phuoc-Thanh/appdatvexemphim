package com.example.appxemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VoucherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoucherFragment extends Fragment {
    private TextView add_uudai;
    private TextView add_voucher;
    private TextView lichsu;
    private LinearLayout linearLayout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VoucherFragment() {
        // Required empty public constructor
    }

    public static VoucherFragment newInstance(String param1, String param2) {
        VoucherFragment fragment = new VoucherFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);

        // Initialize your views
        add_uudai = view.findViewById(R.id.add_uudai);
        add_voucher = view.findViewById(R.id.add_voucher);
        lichsu = view.findViewById(R.id.lichsu);

        // Set onClickListeners to replace fragments
        add_voucher.setOnClickListener(view1 -> replaceFragment(new AddVoucher()));
        add_uudai.setOnClickListener(view12 -> replaceFragment(new UuDai()));
        lichsu.setOnClickListener(view13 -> replaceFragment(new VoucherLichSu()));
        linearLayout=view.findViewById(R.id.linear_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"voucher đã hết lượt sử dụng",Toast.LENGTH_SHORT).show();
                backpage();

            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}
