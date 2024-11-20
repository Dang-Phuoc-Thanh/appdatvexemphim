package com.example.appxemphim.RAP;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxemphim.R;

import java.util.List;

// Lớp RapAdapter mở rộng từ RecyclerView.Adapter
public class RapAdapter extends RecyclerView.Adapter<RapAdapter.RapViewHolder> {

    private List<Rap> rapList;
    private OnItemClickListener listener;

    // Interface để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Rap rap);
    }

    // Constructor của RapAdapter nhận danh sách rạp và listener
    public RapAdapter(List<Rap> rapList, OnItemClickListener listener) {
        this.rapList = rapList;
        this.listener = listener;
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
        holder.itemView.setOnClickListener(v -> listener.onItemClick(rap));
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

    // Phương thức cập nhật danh sách rạp
    public void updateRapList(List<Rap> newRapList) {
        this.rapList.clear();
        this.rapList.addAll(newRapList);
        notifyDataSetChanged(); // Cập nhật dữ liệu
    }
}
