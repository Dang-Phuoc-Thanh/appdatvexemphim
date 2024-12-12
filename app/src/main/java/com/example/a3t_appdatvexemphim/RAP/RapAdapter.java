package com.example.a3t_appdatvexemphim.RAP;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.R;

import java.util.List;

// Lớp RapAdapter mở rộng từ RecyclerView.Adapter
public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {
    private List<Rap> rapList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Rap rap);
    }

    public RapAdapter(List<Rap> rapList, OnItemClickListener listener) {
        this.rapList = rapList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rap, parent, false);
        return new RapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        Rap rap = rapList.get(position);
        holder.bind(rap, listener);
    }

    @Override
    public int getItemCount() {
        return rapList.size();
    }

    public void updateRapList(List<Rap> newRapList) {
        rapList.clear();
        rapList.addAll(newRapList);
        notifyDataSetChanged();
    }

    public static class RapViewHolder extends RecyclerView.ViewHolder {
        private ImageView rapIcon;
        private TextView rapName;
        private TextView rapAddress;
        private TextView rapKhoangCach;

        public RapViewHolder(@NonNull View itemView) {
            super(itemView);
            rapIcon = itemView.findViewById(R.id.rap_icon);
            rapName = itemView.findViewById(R.id.rapName);
            rapAddress = itemView.findViewById(R.id.rapAddress);
            rapKhoangCach = itemView.findViewById(R.id.rapKhoangCach);
        }

        public void bind(final Rap rap, final OnItemClickListener listener) {
            rapName.setText(rap.getTenRap());
            rapAddress.setText(rap.getDiaChi());
            rapKhoangCach.setText(rap.getKhoangCach() + " km");
            Glide.with(itemView.getContext())
                    .load(rap.getHinhAnh())
                    .into(rapIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(rap);
                }
            });
        }
    }
}