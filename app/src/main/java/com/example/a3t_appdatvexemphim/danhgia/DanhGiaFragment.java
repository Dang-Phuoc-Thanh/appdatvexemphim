package com.example.a3t_appdatvexemphim.danhgia;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.KhacFragment;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.TaiKhoan;
import com.example.a3t_appdatvexemphim.VeCuaToiFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhGiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhGiaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_USER_ID = "USER_ID"; // Key để truyền userId qua arguments
    private String userId; // Biến lưu userId
    private int MaPhim; // Biến lưu MaPhim
    private String mParam1;
    private String mParam2;
    private Button gui;
    private ImageView back;
    private ImageView hinh_anh;
    private TextView ten_phim;
    public DanhGiaFragment() {
        // Required empty public constructor
    }

    public static DanhGiaFragment newInstance(String param1, String param2) {
        DanhGiaFragment fragment = new DanhGiaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    // Phương thức factory để tạo instance mới của DanhGiaFragment


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            userId = getArguments().getString("USER_ID"); // Lấy userId từ arguments
            MaPhim = getArguments().getInt("MaPhim"); // Lấy userId từ arguments
            Log.d("CommentFilm_Fragment", "Received User ID: " + userId + " MaPhim: " + MaPhim);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhgia, container, false);
        hinh_anh = view.findViewById(R.id.hinh_anh);
        ten_phim = view.findViewById(R.id.ten_phim);
        gui = view.findViewById(R.id.gui);
        EditText nhanxet = view.findViewById(R.id.nhanxet);
        RatingBar danhgia = view.findViewById(R.id.danhgia);
        //Fragment newFragment = DanhGiaFragment.newInstance(userId); // Truyền userId tới DanhGiaFragment

        if (getArguments() != null) {
            String tenPhim = getArguments().getString("TenPhim1");
            String hinhAnh = getArguments().getString("HinhAnh1");
            //Integer MaPhim = getArguments().getInt("MaPhim");
            // String id = getArguments().getString(ARG_USER_ID);

            // Log the values of id and MaPhim
            Log.d(TAG, "User ID: " + userId);
            Log.d(TAG, "MaPhim: " + MaPhim);

            // Hiển thị dữ liệu lên giao diện
            ten_phim.setText(tenPhim);
            Glide.with(this).load(hinhAnh).into(hinh_anh);
        } else {
            Toast.makeText(getContext(), "chưa gọi được Arguments", Toast.LENGTH_SHORT).show();
        }

        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NhanXet = nhanxet.getText().toString();
                int DanhGia = (int) danhgia.getRating();
                // Integer MaPhim = getArguments().getInt("MaPhim");
                // String id = getArguments().getString(ARG_USER_ID);
// Log the values of id and MaPhim
                Log.d(TAG, "User ID: " + userId);
                Log.d(TAG, "MaPhim: " + MaPhim);
                if (NhanXet.isEmpty() || DanhGia == 0) {
                    Toast.makeText(getContext(), "Vui lòng nhập đánh giá và điểm đánh giá", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference binhLuanRef = FirebaseDatabase.getInstance().getReference("BINHLUAN");
                String MaBinhLuan = binhLuanRef.push().getKey();

                BinhLuan binhLuan = new BinhLuan(MaBinhLuan, String.valueOf(MaPhim), userId, NhanXet, (float) DanhGia);
                binhLuanRef.child(MaBinhLuan).setValue(binhLuan).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Đánh giá thành công", Snackbar.LENGTH_LONG).show();
                            backpage();
                        } else {
                            Toast.makeText(getContext(), "Lỗi khi lưu đánh giá", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        back=view.findViewById(R.id.back);
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