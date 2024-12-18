package com.example.a3t_appdatvexemphim.RAP;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.RAP.LichChieu;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private List<LichChieu> lichChieuList;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener listener;


    public DayAdapter(List<LichChieu> lichChieuList, OnItemClickListener listener) {
        this.lichChieuList = lichChieuList;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(LichChieu lichChieu);
    }


    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        LichChieu lichChieu = lichChieuList.get(position);
        holder.bind(lichChieu, listener);

        // Update the background and text color based on selection
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#2980b9"));
            holder.thu.setTextColor(Color.WHITE);
            holder.ngay.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.thu.setTextColor(Color.BLACK);
            holder.ngay.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lichChieuList.size();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView thu, ngay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            thu = itemView.findViewById(R.id.thu);
            ngay = itemView.findViewById(R.id.ngay);
        }

        // Thêm phương thức bind để đặt dữ liệu vào TextView
        public void bind(final LichChieu lichChieu, final OnItemClickListener listener) {
            thu.setText(lichChieu.getThu());
            ngay.setText(lichChieu.getNgayChieu());

            // Gọi listener khi click vào item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(lichChieu);
                }
            });
        }
    }

}