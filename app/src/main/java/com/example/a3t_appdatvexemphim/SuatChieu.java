package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.RAP.Rap;
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
    private static final String ARG_PARAM3 = "param3";
    private dsFILMHH selectedFilm;
    private Rap selectedRap;

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String userId;
    private int maPhim;

    public SuatChieu() {
        // Required empty public constructor
    }

    public static SuatChieu newInstance(String param1, String param2, String param3) {
        SuatChieu fragment = new SuatChieu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            // Nhận thêm dữ liệu từ CinemaFragment
            String tenPhim = getArguments().getString("TenPhim");
            String noiDung = getArguments().getString("NoiDung");
            String hinhAnh = getArguments().getString("HinhAnh");
            userId = getArguments().getString("USER_ID"); // Lấy userId từ arguments
            maPhim= getArguments().getInt("MaPhim");
            Log.d("SuatChieu", "Received User ID: " + userId + " MaPhim: " + maPhim);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suat_chieu, container, false);
        linear1 = view.findViewById(R.id.linear1);
        butback = view.findViewById(R.id.imageView2);

        // Nhận dữ liệu từ Bundle
        List<ClassPhim> danhsachphim = null;

        if (getArguments() != null) {
            selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
            danhsachphim = getArguments().getParcelableArrayList("danhsachphim");
        } else {
            selectedFilm = null; // Initialize to null if no arguments
        }



        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backpage();
            }
        });

        TextView filmNameTextView = view.findViewById(R.id.textView8);
        ImageView filmImage = view.findViewById(R.id.imageView3);
        TextView contentFilmTextView = view.findViewById(R.id.textView9);

        if (getArguments() != null) {
            String tenPhim = getArguments().getString("TenPhim");
            String noiDung = getArguments().getString("NoiDung");
            String hinhAnh = getArguments().getString("HinhAnh");

            // Hiển thị dữ liệu lên giao diện
            filmNameTextView.setText(tenPhim);
            contentFilmTextView.setText(noiDung);
            Glide.with(this).load(hinhAnh).into(filmImage);
            linear1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Tạo fragment mới
                    Fragment newFragment = new DatGheFragment();

                    // Tạo Bundle để truyền dữ liệu
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selectedFilm", selectedFilm); // Truyền selectedFilm vào Bundle
                    bundle.putString("USER_ID", userId); // Truyền userId
                    // Hiển thị thông báo
                    Toast.makeText(getContext(), "Thông tin phim đã được lưu vào Bundle", Toast.LENGTH_SHORT).show();

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

        return view;
    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}
