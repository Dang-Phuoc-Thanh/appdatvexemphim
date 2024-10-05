package category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;

import java.util.List;

import FILM.FilmAdapter;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context nContext;
    private List<Category> nListCategory;

    public CategoryAdapter(Context nContext){
        this.nContext=nContext;
    }
    public void setData(List<Category> list){
        this.nListCategory=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
Category category =nListCategory.get(position);
if(category==null){
    return;
}
holder.tvNameCategory.setText(category.getNameCategory());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(nContext,RecyclerView.HORIZONTAL,false);
        holder.rcvFilm.setLayoutManager(linearLayoutManager);
        FilmAdapter filmAdapter=new FilmAdapter();
        filmAdapter.setData(category.getFilms());
        holder.rcvFilm.setAdapter(filmAdapter);
    }

    @Override
    public int getItemCount() {
        if(nListCategory!= null){
            return nListCategory.size();
        }
        return 0;
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameCategory;
        private RecyclerView rcvFilm;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory=itemView.findViewById(R.id.tv_name_category);
            rcvFilm=itemView.findViewById(R.id.rcv_film);
        }
    }
}
