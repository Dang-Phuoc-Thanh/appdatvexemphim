package com.example.appxemphim.DSphim;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appxemphim.CommentFilm_Fragment;
import com.example.appxemphim.R;
import com.example.appxemphim.SuatChieu;
import com.example.appxemphim.VoucherFragment;

import java.util.ArrayList;

public class listAdapter extends ArrayAdapter<dsFILMHH> {

    public listAdapter(Context context, ArrayList<dsFILMHH> dsFILMHHArrayList) {
        super(context, R.layout.list_item_filmhoathinh, dsFILMHHArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        dsFILMHH dsfilmhh = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_filmhoathinh, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView filmName = convertView.findViewById(R.id.NameFilm);
        TextView filmTime = convertView.findViewById(R.id.timeFilm);
        TextView filmDay = convertView.findViewById(R.id.dayFilm);
        Button filmDatVe = convertView.findViewById(R.id.btnDatVe);

        // Thiết lập dữ liệu
        imageView.setImageResource(dsfilmhh.imageID);
        filmName.setText(dsfilmhh.name);
        filmTime.setText(dsfilmhh.time);
        filmDay.setText(dsfilmhh.day);
        filmDatVe.setText(dsfilmhh.datve);

        // Thêm sự kiện click cho nút "Đặt vé"
        filmDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new SuatChieu());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        filmName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new CommentFilm_Fragment());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return convertView;
    }
}