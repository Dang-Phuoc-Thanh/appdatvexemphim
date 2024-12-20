package com.example.a3t_appdatvexemphim.Video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.CommentFilm_Fragment;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.RAP.CinemaFragment;
import com.example.a3t_appdatvexemphim.Trangchu.FILM;
import com.example.a3t_appdatvexemphim.Trangchu.FilmAdapter1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Video_Fragment extends Fragment {
    private static final String TAG = "Video_Fragment";
    private RecyclerView rcvCategory;
    private FilmAdapter1 filmAdapter;
    private List<FILM> phimDeXuat;
    private DatabaseReference mData;
    private String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            userId = getArguments().getString("USER_ID"); // Lấy userId từ arguments
            Log.d("Video_Fragment", "Received User ID: " + userId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Khởi tạo Firebase Database
        mData = FirebaseDatabase.getInstance().getReference();

        // Khởi tạo RecyclerView
        rcvCategory = view.findViewById(R.id.rcv_category);
        if (rcvCategory == null) {
            Log.e(TAG, "RecyclerView is null");
            return view;
        }

        // Khởi tạo adapter và gán nó cho RecyclerView
        phimDeXuat = new ArrayList<>();
        filmAdapter = new FilmAdapter1(getContext(), phimDeXuat);
        rcvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvCategory.setAdapter(filmAdapter);

        // Lấy dữ liệu phim được chọn từ các đối số
        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");

        // Hiển thị trailer của phim được chọn
        if (selectedFilm != null) {
            VideoView videoView = view.findViewById(R.id.video_view);
            Uri videoUri = Uri.parse(selectedFilm.getTrailerUrl());
            videoView.setVideoURI(videoUri);

            // Đặt listener để xử lý lỗi phát video
            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getContext(), "Can't play video", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            videoView.start();

            // Đặt tên phim và mô tả
            TextView tvTenPhim = view.findViewById(R.id.tvTenPhim);
            TextView tvMoTa = view.findViewById(R.id.tvMoTa);
            TextView tvTitle = view.findViewById(R.id.tvTitle);

            tvTenPhim.setText(selectedFilm.getName());
            tvTitle.setText(selectedFilm.getName());
            tvMoTa.setText("Nội dung: " + selectedFilm.getNoidung());
        }

        // Tải danh sách phim cùng thể loại
        if (selectedFilm != null) {
            loadMoviesByGenreData(selectedFilm.getName());
        }
        // Set click listener for btnBack
        ImageView btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        // Set click listener for btnDatVeNgay Và truyền dữ liệu qua CinemaFragment
        Button btnDatVeNgay = view.findViewById(R.id.btnDatVeNgay);
        btnDatVeNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang Video_Fragment
                CinemaFragment cinemaFragment = new CinemaFragment();

                // Tùy chọn: Truyền dữ liệu vào Video_Fragment nếu cần thiết
                Bundle bundle = new Bundle();
                if (selectedFilm != null) {
                    Integer maPhim = selectedFilm.getMaPhim(); // Lấy mã phim từ selectedFilm
                    if (maPhim != null) {
                        navigateToCinemaFragment(maPhim);
                    } else {
                        Toast.makeText(getContext(), "Mã phim không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Phim chưa được chọn", Toast.LENGTH_SHORT).show();
                }
                    bundle.putSerializable("selectedFilm", selectedFilm); // Truyền thông tin phim nếu cần

                bundle.putString("USER_ID", userId); // Truyền userId
                cinemaFragment.setArguments(bundle);

                // Thay thế fragment hiện tại bằng Video_Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, cinemaFragment);
                transaction.addToBackStack(null); // Quay lại khi nhấn Back
                transaction.commit();
            }
        });



        return view;
    }

    public void loadMoviesByGenreData(final String tenPhimInput) {
        // Initialize phimDeXuat list
        phimDeXuat = new ArrayList<>();

        // Truy vấn để lấy mã thể loại phim từ PHIM dựa vào tên phim
        mData.child("PHIM").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer maTheLoaiPhimInput = null;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tenPhim = snapshot.child("TenPhim").getValue(String.class);
                    String hinhAnh = snapshot.child("HinhAnh").getValue(String.class);
                    Integer maPhim= snapshot.child("MaPhim").getValue(Integer.class);
                    Log.d(TAG, "Checking movie: " + tenPhim); // Log tên phim để kiểm tra
                    Log.d(TAG, "Checking image: " + hinhAnh); // Log hình ảnh để kiểm tra
                    Log.d(TAG, "Checking maPhim: " + maPhim); // Log hình ảnh để kiểm tra
                    // Tạo một đối tượng FILM mới và đặt các thuộc tính của nó
                    FILM phim = new FILM(tenPhim, hinhAnh, maPhim);
                    phimDeXuat.add(phim);

                    if (tenPhim != null && tenPhim.equals(tenPhimInput)) {
                        maTheLoaiPhimInput = snapshot.child("MaTheLoaiPhim").getValue(Integer.class);
                        Log.d(TAG, "Found genre ID: " + maTheLoaiPhimInput); // Log mã thể loại phim
                        break;
                    }
                }

                // Cập nhật RecyclerView với danh sách phimDeXuat
                if (rcvCategory != null) {
                    filmAdapter.setData(phimDeXuat);
                } else {
                    Log.e(TAG, "RecyclerView is null when setting adapter data");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("loadMoviesByGenreData", "Lỗi khi truy vấn dữ liệu: " + databaseError.getMessage());
            }
        });
    }

    private void navigateToCinemaFragment(Integer maPhim) {
        CinemaFragment cinemaFragment = new CinemaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("MaPhim_tuVideo", maPhim); // Truyền maPhim
        bundle.putString("USER_ID", userId); // Truyền userId
        cinemaFragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, cinemaFragment);
        transaction.addToBackStack(null); // Quay lại khi nhấn Back
        transaction.commit();
    }

}
