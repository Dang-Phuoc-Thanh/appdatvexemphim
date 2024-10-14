package com.example.a3t_appdatvexemphim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<dsFILMHH> {

    public listAdapter(Context context,ArrayList<dsFILMHH> dsFILMHHArrayList){
        super(context, R.layout.list_item_filmhoathinh,dsFILMHHArrayList);
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

        // Thiết lập dữ liệu cho từng phần tử
        imageView.setImageResource(dsfilmhh.imageID);
        filmName.setText(dsfilmhh.name);
        filmTime.setText(dsfilmhh.time);
        filmDay.setText(dsfilmhh.day);
        filmDatVe.setText(dsfilmhh.datve);

        return convertView;  // Trả về view đã được gán dữ liệu
    }

}
