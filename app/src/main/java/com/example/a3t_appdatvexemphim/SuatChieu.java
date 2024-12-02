package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private ImageView imageView2;
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

        // Nhận dữ liệu từ Bundle
        List<ClassPhim> danhsachphim = null;
        dsFILMHH selectedFilm = null;
        if (getArguments() != null) {
            selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
            danhsachphim = getArguments().getParcelableArrayList("danhsachphim");
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
            }
        }

        return view;
    }
}