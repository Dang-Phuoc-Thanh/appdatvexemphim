package com.example.a3t_appdatvexemphim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VoucherAdapter extends ArrayAdapter<Voucher> {
    private Context context;
    private ArrayList<Voucher> voucherList;

    public VoucherAdapter(Context context, ArrayList<Voucher> voucherList) {
        super(context, 0, voucherList);
        this.context = context;
        this.voucherList = voucherList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Inflate the custom layout for each item in the list
            convertView = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
            holder = new ViewHolder();
            holder.imgVoucher = convertView.findViewById(R.id.img_voucher);
            holder.tvVoucher = convertView.findViewById(R.id.tv_voucher);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current voucher
        Voucher voucher = voucherList.get(position);

        // Set data to views
        holder.tvVoucher.setText(voucher.getTitle() + "\n" + voucher.getDescription());

        // Use Glide to load images from URL
        Glide.with(context)
                .load(voucher.getImageUrl()) // URL of the image
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
                .error(R.drawable.ic_launcher_background) // Error image
                .into(holder.imgVoucher);

        return convertView;
    }

    // ViewHolder class for caching views
    static class ViewHolder {
        ImageView imgVoucher;
        TextView tvVoucher;
    }
}
