// FilmAdapter.java
package com.example.a3t_appdatvexemphim.Trangchu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.THONGTINPHIM.TTPhimFragment;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<FILM> nFilms;
    private FragmentActivity activity;

    public FilmAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

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
        holder.imgFilm.setImageResource(film.getResourceId());
        holder.tvTitle.setText(film.getTitle());

        // Set OnClickListener for img_phim2
        if (film.getResourceId() == R.drawable.img_phim2) {
            holder.imgFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, new TTPhimFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (nFilms != null) {
            return nFilms.size();
        }
        return 0;
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