package com.example.a3t_appdatvexemphim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Lớp RapAdapter mở rộng từ RecyclerView.Adapter
public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {

    private List<Rap> rapList;

    // Constructor của RapAdapter nhận danh sách rạp
    public RapAdapter(List<Rap> rapList) {
        this.rapList = rapList;
    }

    // Tạo ViewHolder cho các item của RecyclerView
    @NonNull
    @Override
    public RapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout cho từng item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rap, parent, false);
        return new RapViewHolder(view);
    }

    // Ánh xạ dữ liệu cho các view trong ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RapViewHolder holder, int position) {
        Rap rap = rapList.get(position);
        holder.rapName.setText(rap.getName());
        holder.rapAddress.setText(rap.getAddress());
    }

    // Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return rapList.size();
    }

    // Lớp ViewHolder giúp ánh xạ các view của từng item
    static class RapViewHolder extends RecyclerView.ViewHolder {
        TextView rapName;
        TextView rapAddress;

        public RapViewHolder(@NonNull View itemView) {
            super(itemView);
            // Khởi tạo các view từ layout item_rap.xml
            rapName = itemView.findViewById(R.id.rapName);
            rapAddress = itemView.findViewById(R.id.rapAddress);
        }
    }
}
