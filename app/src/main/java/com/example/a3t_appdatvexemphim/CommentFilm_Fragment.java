package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.DSphimhhFragment;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFilm_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFilm_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView back;
    private ImageView imgFilm;
    private TextView nameFilm;
    private TextView contentFilm;

    public CommentFilm_Fragment() {
        // Required empty public constructor
    }

    public static CommentFilm_Fragment newInstance(String param1, String param2) {
        CommentFilm_Fragment fragment = new CommentFilm_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_comment_film_, container, false);

        // Nhận dữ liệu từ Bundle
        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
        ArrayList<ClassPhim> danhsachphim = getArguments().getParcelableArrayList("danhsachphim");

        // Khởi tạo các thành phần giao diện
        imgFilm = view.findViewById(R.id.img_film);
        nameFilm = view.findViewById(R.id.name_film);
        contentFilm = view.findViewById(R.id.Content_film);
        back = view.findViewById(R.id.back);

        // Thiết lập dữ liệu cho các thành phần giao diện
        if (selectedFilm != null) {
            Glide.with(this).load(selectedFilm.getImageUrl()).into(imgFilm);
            nameFilm.setText(selectedFilm.getName());
            contentFilm.setText(selectedFilm.getDatve()); // Giả sử bạn muốn hiển thị thông tin đặt vé
        }

        // Sử dụng dữ liệu của selectedFilm nếu cần thiết
        if (danhsachphim != null && selectedFilm != null) {
            int i = 0;
            while (i < danhsachphim.size() && !selectedFilm.getName().equals(danhsachphim.get(i).TenPhim)) {
                i++;
            }

            if (i < danhsachphim.size()) {
                // Sử dụng dữ liệu của selectedFilm nếu cần thiết
                nameFilm.setText(danhsachphim.get(i).TenPhim);
                Glide.with(this).load(danhsachphim.get(i).HinhAnh).into(imgFilm); // Sử dụng Glide để tải hình ảnh
                contentFilm.setText(danhsachphim.get(i).NoiDung);
            }
        }

        // Sự kiện click cho nút quay lại
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new DSphimhhFragment(); // Replace with your target fragment

                // Truyền lại danh sách phim nếu cần thiết
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("danhsachphim", danhsachphim);
                newFragment.setArguments(bundle);

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}