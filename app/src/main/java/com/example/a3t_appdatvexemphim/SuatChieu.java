package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.DSphim.DSphimhhFragment;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuatChieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuatChieu extends Fragment {
    CardView rapchieu1;
    private LinearLayout linear1;
    private ImageView butback;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SuatChieu() {
        // Required empty public constructor
    }

    public static SuatChieu newInstance(String param1, String param2) {
        SuatChieu fragment = new SuatChieu();
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
        View view = inflater.inflate(R.layout.fragment_suat_chieu, container, false);
        linear1 = view.findViewById(R.id.linear1);
        butback = view.findViewById(R.id.imageView2);

        // Nhận dữ liệu từ Bundle
        List<ClassPhim> danhsachphim = null;
        final dsFILMHH selectedFilm; // Declare as final
        if (getArguments() != null) {
            selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
            danhsachphim = getArguments().getParcelableArrayList("danhsachphim");
        } else {
            selectedFilm = null; // Initialize to null if no arguments
        }

        // Sử dụng dữ liệu của selectedFilm nếu cần thiết
        if (danhsachphim != null && selectedFilm != null) {
            int i = 0;
            while (i < danhsachphim.size() && !selectedFilm.getName().equals(danhsachphim.get(i).TenPhim)) {
                i++;
            }

            if (i < danhsachphim.size()) {
                // Sử dụng dữ liệu của selectedFilm nếu cần thiết
                TextView filmNameTextView = view.findViewById(R.id.textView8);
                ImageView filmImage = view.findViewById(R.id.imageView3);
                TextView Contenfilm = view.findViewById(R.id.textView9);

                filmNameTextView.setText(danhsachphim.get(i).TenPhim);
                Glide.with(this).load(danhsachphim.get(i).HinhAnh).into(filmImage); // Sử dụng Glide để tải hình ảnh
                Contenfilm.setText(danhsachphim.get(i).NoiDung);
                linear1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Tạo fragment mới
                        Fragment newFragment = new DatGheFragment();

                        // Tạo Bundle để truyền dữ liệu
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selectedFilm", selectedFilm); // Truyền selectedFilm vào Bundle

                        // Đặt Bundle vào fragment
                        newFragment.setArguments(bundle);

                        // Chuyển sang fragment mới
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, newFragment); // Thay đổi Fragment
                        fragmentTransaction.addToBackStack(null); // Thêm vào back stack
                        fragmentTransaction.commit();
                    }
                });
            }
        }

        butback.setOnClickListener(new View.OnClickListener() {
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