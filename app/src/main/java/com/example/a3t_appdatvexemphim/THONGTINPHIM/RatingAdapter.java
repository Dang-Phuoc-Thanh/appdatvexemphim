package com.example.a3t_appdatvexemphim.THONGTINPHIM;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3t_appdatvexemphim.R;

import java.util.List;

public class RatingAdapter extends ArrayAdapter<binhluan> {
    public RatingAdapter(Context context, List<binhluan> reviews) {
        super(context, 0, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Lấy dữ liệu đánh giá
        binhluan bl = getItem(position);

        // Kiểm tra và tái sử dụng view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_binhluan, parent, false);
        }
        // Khai báo và gán giá trị cho các thành phần trong item_rating.xml
        TextView nameTextView = convertView.findViewById(R.id.text_name);
        TextView commentTextView = convertView.findViewById(R.id.text_binhluan);
        ImageView ratingIcon = convertView.findViewById(R.id.rating_icon);
        TextView ratingTextView = convertView.findViewById(R.id.rating_text);

        nameTextView.setText(bl.getName());
        commentTextView.setText(bl.getComment());
        ratingTextView.setText(bl.getRating());
        // Gán hình ảnh cho ratingIcon nếu cần

        return convertView;
    }
}
