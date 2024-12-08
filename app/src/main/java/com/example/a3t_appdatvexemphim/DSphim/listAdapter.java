package com.example.a3t_appdatvexemphim.DSphim;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.CommentFilm_Fragment;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.SuatChieu;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<dsFILMHH> {
    private ArrayList<ClassPhim> danhsachphim; // Thêm biến thành viên

    public listAdapter(Context context, ArrayList<dsFILMHH> dsFILMHHArrayList, ArrayList<ClassPhim> danhsachphim) {
        super(context, R.layout.list_item_filmhoathinh, dsFILMHHArrayList);
        this.danhsachphim = danhsachphim; // Khởi tạo biến thành viên
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dsFILMHH dsfilmhh = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_filmhoathinh, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView filmName = convertView.findViewById(R.id.NameFilm);
        TextView filmTime = convertView.findViewById(R.id.timeFilm);
        TextView filmDay = convertView.findViewById(R.id.dayFilm);
        Button filmDatVe = convertView.findViewById(R.id.btnDatVe);

        // Thiết lập dữ liệu
        Glide.with(getContext()).load(dsfilmhh.imageUrl).into(imageView);
        filmName.setText(dsfilmhh.name);
        filmTime.setText(dsfilmhh.time);
        filmDay.setText(dsfilmhh.day);
        filmDatVe.setText(dsfilmhh.datve);

        // Thêm sự kiện click cho nút "Đặt vé"
        filmDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", dsfilmhh); // Truyền mục đã chọn
                bundle.putParcelableArrayList("danhsachphim", danhsachphim); // Truyền danh sách phim

                // Tạo một instance của SuatChieu fragment
                SuatChieu suatChieuFragment = new SuatChieu();
                suatChieuFragment.setArguments(bundle);

                // Thay thế fragment hiện tại bằng SuatChieu fragment
                FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, suatChieuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Thêm sự kiện click cho tên phim
        filmName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", dsfilmhh); // Truyền mục đã chọn
                bundle.putParcelableArrayList("danhsachphim", danhsachphim); // Truyền danh sách phim

                // Tạo một instance của CommentFilm_Fragment
                CommentFilm_Fragment commentFilmFragment = new CommentFilm_Fragment();
                commentFilmFragment.setArguments(bundle);

                // Thay thế fragment hiện tại bằng CommentFilm_Fragment
                FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, commentFilmFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return convertView;
    }
}