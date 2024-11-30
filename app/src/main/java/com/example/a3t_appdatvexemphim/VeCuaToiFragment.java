package com.example.a3t_appdatvexemphim;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3t_appdatvexemphim.THONGTINPHIM.TTPhimFragment;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.example.a3t_appdatvexemphim.danhgia.DanhGiaFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VeCuaToiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VeCuaToiFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView comment_film;
    private String mParam1;
    private String mParam2;
    private ImageView back;
    private boolean isExpanded = false;

    public VeCuaToiFragment() {
        // Required empty public constructor
    }

    public static VeCuaToiFragment newInstance(String param1, String param2) {
        VeCuaToiFragment fragment = new VeCuaToiFragment();
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
        View view = inflater.inflate(R.layout.fragment_ve_cua_toi, container, false);

        final ConstraintLayout moreContent = view.findViewById(R.id.more_content);
        final TextView showMoreButton = view.findViewById(R.id.show_more_button);

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    moreContent.setVisibility(View.GONE);
                    showMoreButton.setText("Xem chi tiết");
                } else {
                    moreContent.setVisibility(View.VISIBLE);
                    showMoreButton.setText("Thu gọn");
                }
                isExpanded = !isExpanded;
            }
        });
        comment_film=view.findViewById(R.id.comment_film);
        comment_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new DanhGiaFragment();

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        back=view.findViewById(R.id.imageView4);
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
