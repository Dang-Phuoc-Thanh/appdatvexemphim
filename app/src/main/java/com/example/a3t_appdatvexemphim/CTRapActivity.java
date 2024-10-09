package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a3t_appdatvexemphim.databinding.ActivityCtrapBinding;  // Đổi sang layout đúng của CTRapActivity


import java.util.ArrayList;

public class CTRapActivity extends AppCompatActivity {
    ActivityCtrapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ctrap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityCtrapBinding.inflate(getLayoutInflater());
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
        listAdapter ListAdapter = new listAdapter(CTRapActivity.this, dsFILMHHArrayList);

        // Gán adapter cho ListView
        binding.listview.setAdapter(ListAdapter);

        // Bắt sự kiện click cho ListView
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
