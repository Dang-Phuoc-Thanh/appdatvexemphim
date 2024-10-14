package FILM;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;

import java.util.List;
import java.util.logging.Level;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder>{

   private List<FILM> nFilms;
   public void setData(List<FILM> list){
       this.nFilms=list;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_films,parent,false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
FILM film=nFilms.get(position);
if(film==null){
    return;
}
holder.imgFilm.setImageResource(film.getResourceId());
holder.tvTitle.setText(film.getTitle());
    }

    @Override
    public int getItemCount() {
       if(nFilms!=null){
           return nFilms.size();
       }

        return 0;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFilm;
        private TextView tvTitle;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm=itemView.findViewById(R.id.img_film);
            tvTitle=itemView.findViewById(R.id.text_title);
        }
    }
}
