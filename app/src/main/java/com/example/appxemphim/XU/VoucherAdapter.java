package com.example.appxemphim.XU;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxemphim.R;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private ArrayList<Voucher_xu> voucherList;

    public VoucherAdapter(ArrayList<Voucher_xu> voucherList) {
        this.voucherList = voucherList;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xu_voucher, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher_xu voucher_xu = voucherList.get(position);

        // Thiết lập nội dung cho các thành phần UI trong item
        holder.textViewTitle.setText(voucher_xu.getTitle());
        holder.textViewBrand.setText(voucher_xu.getBrand());
        holder.textViewCoin.setText(String.valueOf(voucher_xu.getCoin()) + " xu");

        // Thiết lập hình ảnh cửa hàng và icon xu nếu cần
        holder.imageViewStore.setImageResource(voucher_xu.getStoreImageResId()); // Sử dụng phương thức để lấy hình ảnh cửa hàng
        holder.imageViewCoinIcon.setImageResource(R.drawable.dollar); // Icon xu mặc định
    }

    @Override
    public int getItemCount() {
        return voucherList.size();
    }

    public static class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewBrand, textViewCoin;
        ImageView imageViewStore, imageViewCoinIcon;

        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ các thành phần UI từ layout
            textViewTitle = itemView.findViewById(R.id.tvVoucherTitle);
            textViewBrand = itemView.findViewById(R.id.tvVoucherDon);
            textViewCoin = itemView.findViewById(R.id.tvCoin);
            imageViewStore = itemView.findViewById(R.id.imgCuaHang);
            imageViewCoinIcon = itemView.findViewById(R.id.imgCoinIcon);
        }
    }
}
