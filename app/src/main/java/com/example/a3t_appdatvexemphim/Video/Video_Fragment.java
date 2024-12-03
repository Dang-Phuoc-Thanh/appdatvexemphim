package com.example.a3t_appdatvexemphim.Video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.listAdapter;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
import com.example.a3t_appdatvexemphim.Trangchu.FILM;
import com.example.a3t_appdatvexemphim.Trangchu.FilmAdapter;

import java.util.ArrayList;
import java.util.List;

public class Video_Fragment extends Fragment {
    private ListView DSphim;
    private ArrayList<dsFILMHH> list;
    private RecyclerView rcvCategory;
    private FilmAdapter filmAdapter;
    private List<ClassPhim> filteredFilms = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Retrieve the selected movie data from the arguments
        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
        List<ClassPhim> danhsachphim = getArguments().getParcelableArrayList("danhsachphim");

        // Display the selected movie's trailer
        if (selectedFilm != null) {
            VideoView videoView = view.findViewById(R.id.video_view);
            videoView.setVideoPath(selectedFilm.getTrailerUrl());

            // Set an error listener to handle video playback errors
            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getContext(), "Can't play video", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            videoView.start();

            // Set the movie name and description
            TextView tvTenPhim = view.findViewById(R.id.tvTenPhim);
            TextView tvMoTa = view.findViewById(R.id.tvMoTa);
            TextView tvTitle = view.findViewById(R.id.tvTitle);

            tvTenPhim.setText(selectedFilm.getName());
            tvTitle.setText(selectedFilm.getName());
            tvMoTa.setText("Nội dung: " + selectedFilm.getNoidung());


        }
        return view;
    }
}
