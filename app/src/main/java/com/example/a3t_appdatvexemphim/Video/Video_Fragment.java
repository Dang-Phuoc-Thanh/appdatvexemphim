// Video_Fragment.java
package com.example.a3t_appdatvexemphim.Video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Trangchu.FILM;
import com.example.a3t_appdatvexemphim.Trangchu.FilmAdapter;

import java.util.ArrayList;
import java.util.List;

public class Video_Fragment extends Fragment {

    private RecyclerView rcvCategory;
    private FilmAdapter filmAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        rcvCategory = view.findViewById(R.id.rcv_category);
        filmAdapter = new FilmAdapter((FragmentActivity) getActivity());
        filmAdapter.setData(getListFilms());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        rcvCategory.setAdapter(filmAdapter);

        return view;
    }

    private List<FILM> getListFilms() {
        List<FILM> listFilms = new ArrayList<>();
        listFilms.add(new FILM("Đẹp trai thấy sai sai", R.drawable.img_phim2));
        listFilms.add(new FILM("Đố anh còng được tôi", R.drawable.phd2));
        listFilms.add(new FILM("Mạng đổi mạng", R.drawable.phd3));
        listFilms.add(new FILM("Đặc vụ xuyên quốc gia", R.drawable.phd4));
        listFilms.add(new FILM("Trừng phạt", R.drawable.phd5));
        return listFilms;
    }
}