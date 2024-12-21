package com.example.a3t_appdatvexemphim.danhgia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;

import java.util.List;
import java.util.Map;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder> {

    private List<BinhLuan> binhLuanList;
    private Map<String, String> userNames;

    public BinhLuanAdapter(List<BinhLuan> binhLuanList, Map<String, String> userNames) {
        this.binhLuanList = binhLuanList;
        this.userNames = userNames;
    }

    @NonNull
    @Override
    public BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_binhluan, parent, false);
        return new BinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanViewHolder holder, int position) {
        BinhLuan binhLuan = binhLuanList.get(position);

        String userName = userNames.getOrDefault(binhLuan.getId(), "Người dùng ẩn danh");
        holder.textName.setText(userName);
        holder.textBinhLuan.setText(binhLuan.getNhanXet());
        holder.ratingText.setText("Đánh giá: " + binhLuan.getDanhGia() + " sao");
    }

    @Override
    public int getItemCount() {
        return binhLuanList.size();
    }

    public static class BinhLuanViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textBinhLuan, ratingText;

        public BinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textBinhLuan = itemView.findViewById(R.id.text_binhluan);
            ratingText = itemView.findViewById(R.id.rating_text);
        }
    }
}
