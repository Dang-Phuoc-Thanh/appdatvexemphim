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
import com.example.a3t_appdatvexemphim.Video.Video_Fragment;

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
        TextView trailer = view.findViewById(R.id.trailer); // Ánh xạ TextView trailer

        // Thiết lập dữ liệu cho các thành phần giao diện
        if (selectedFilm != null) {
            Glide.with(this).load(selectedFilm.getImageUrl()).into(imgFilm);
            nameFilm.setText(selectedFilm.getName());
            contentFilm.setText(selectedFilm.getNoidung()); // Giả sử bạn muốn hiển thị thông tin đặt vé
        }

        // Sự kiện click cho nút trailer
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Video_Fragment
                Video_Fragment videoFragment = new Video_Fragment();

                // Tùy chọn: Truyền dữ liệu vào Video_Fragment nếu cần thiết
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", selectedFilm); // Truyền thông tin phim nếu cần
                videoFragment.setArguments(bundle);

                // Thay thế fragment hiện tại bằng Video_Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, videoFragment);
                transaction.addToBackStack(null); // Quay lại khi nhấn Back
                transaction.commit();
            }
        });

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
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}