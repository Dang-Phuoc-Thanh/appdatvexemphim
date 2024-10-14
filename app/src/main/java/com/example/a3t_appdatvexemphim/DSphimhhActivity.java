package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3t_appdatvexemphim.databinding.ActivityDsphimhhBinding;

import java.util.ArrayList;

public class DSphimhhActivity extends AppCompatActivity {

    ActivityDsphimhhBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDsphimhhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Dữ liệu cho danh sách phim
        int[] imageId = {R.drawable.phim6, R.drawable.phh11, R.drawable.phh2, R.drawable.phh3, R.drawable.phh5, R.drawable.phh6, R.drawable.phh11};
        String[] name = {"SPY x FAMILY", "Xác ướp - Cuộc phiêu lưu đến LonDon", "Mèo béo siêu đẳng", "SPY x FAMILY", "Bản giao hưởng địa cầu", "Truyền thuyết nhẫn thuật ninja", "Sonic the Hedgehog 2 (2024)"};
        String[] time = {"Thời lượng: 180 Phút", "Thời lượng: 90 Phút", "Thời lượng: 150 Phút", "Thời lượng: 200 Phút", "Thời lượng: 90 Phút", "Thời lượng: 180 Phút", "Thời lượng: 150 Phút"};
        String[] day = {"Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024", "Khởi chiếu: 20/09/2024"};
        String[] datve = {"Đặt vé", "Đặt vé", "Đặt vé", "Đặt vé", "Đặt vé", "Đặt vé", "Đặt vé"};

        // Tạo danh sách đối tượng dsFILMHH
        ArrayList<dsFILMHH> dsFILMHHArrayList = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            dsFILMHH dsfilmhh = new dsFILMHH(name[i], time[i], day[i], datve[i], imageId[i]);
            dsFILMHHArrayList.add(dsfilmhh);
        }

        // Khởi tạo adapter
        listAdapter ListAdapter = new listAdapter(DSphimhhActivity.this, dsFILMHHArrayList);

        // Gán adapter cho ListView
        binding.listview.setAdapter(ListAdapter);

        // Bắt sự kiện click cho ListView
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        // Thêm sự kiện click cho ic_quaylai
        binding.icQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về giao diện TrangchuActivity
                Intent intent = new Intent(DSphimhhActivity.this, TrangchuActivity.class);
                startActivity(intent);
                finish(); // Để không giữ lại DSphimhhActivity trong stack
            }
        });
    }

}
