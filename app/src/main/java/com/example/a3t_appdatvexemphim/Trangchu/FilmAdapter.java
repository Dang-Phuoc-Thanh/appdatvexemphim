package com.example.a3t_appdatvexemphim.Trangchu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Trangchu.FILM;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<FILM> nFilms;

    public void setData(List<FILM> list) {
        this.nFilms = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_films_trangchu, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        FILM film = nFilms.get(position);
        if (film == null) {
            return;
        }
        holder.tvTitle.setText(film.getTitle());
        // Sử dụng Glide để tải hình ảnh từ URL
        Glide.with(holder.itemView.getContext())
                .load(film.getImageUrl())
                .into(holder.imgFilm);
    }

    @Override
    public int getItemCount() {
        return nFilms != null ? nFilms.size() : 0;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFilm;
        private TextView tvTitle;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.img_film);
            tvTitle = itemView.findViewById(R.id.text_title);
        }
    }
}
