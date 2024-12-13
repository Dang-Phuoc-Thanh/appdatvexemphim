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

public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {
    private List<Rap> rapList;
    private OnItemClickListener listener;

    // Interface xử lý sự kiện nhấn
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
        return new RapViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        Rap rap = rapList.get(position);
        holder.bind(rap);
    }

    @Override
    public int getItemCount() {
        return rapList == null ? 0 : rapList.size();
    }

    // Cập nhật danh sách với dữ liệu mới
    public void updateRapList(List<Rap> newRapList) {
        if (rapList != null) {
            rapList.clear();
            rapList.addAll(newRapList);
        } else {
            rapList = newRapList;
        }
        notifyDataSetChanged();
    }

    public static class RapViewHolder extends RecyclerView.ViewHolder {
        private final ImageView rapIcon;
        private final TextView rapName;
        private final TextView rapAddress;
        private final TextView rapKhoangCach;
        private final ImageView arrowIcon;

        public RapViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            rapIcon = itemView.findViewById(R.id.rap_icon);
            rapName = itemView.findViewById(R.id.rapName);
            rapAddress = itemView.findViewById(R.id.rapAddress);
            rapKhoangCach = itemView.findViewById(R.id.rapKhoangCach);
            arrowIcon = itemView.findViewById(R.id.arrowIcon);

            // Sự kiện nhấn vào toàn bộ item
            itemView.setOnClickListener(v -> listener.onItemClick((Rap) itemView.getTag()));

            // Sự kiện nhấn riêng vào mũi tên (arrowIcon)
            arrowIcon.setOnClickListener(v -> listener.onItemClick((Rap) itemView.getTag()));
        }

        public void bind(final Rap rap) {
            rapName.setText(rap.getTenRap());
            rapAddress.setText(rap.getDiaChi());
            rapKhoangCach.setText(rap.getKhoangCach() + " km");

            // Tải ảnh bằng Glide, thêm ảnh placeholder và xử lý lỗi
            Glide.with(itemView.getContext())
                    .load(rap.getHinhAnh())
                    .placeholder(R.drawable.notfound) // Ảnh chờ
                    .error(R.drawable.error)             // Ảnh lỗi
                    .into(rapIcon);

            // Gắn đối tượng `Rap` vào itemView để xử lý sự kiện
            itemView.setTag(rap);
        }
    }
}
