package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class rapbapnuocActivity extends AppCompatActivity implements RapAdapter.OnItemClickListener {
    private TextView cityName;
    private RecyclerView nearbyRapRecyclerView;
    private Spinner spinnerCities;  // Khai báo Spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rapbapnuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityName = findViewById(R.id.textView3);
        nearbyRapRecyclerView = findViewById(R.id.nearbyRapRecyclerView);
        spinnerCities = findViewById(R.id.spinnerCities);  // Khởi tạo Spinner

        // Thiết lập RecyclerView
        nearbyRapRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo adapter với cả hai tham số
        nearbyRapRecyclerView.setAdapter(new RapAdapter(getNearbyRaps(), this));

        // Thay đổi màu chữ thành phố thành trắng
        cityName.setTextColor(getResources().getColor(android.R.color.white));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_list, android.R.layout.simple_spinner_item);
        adapter.setNotifyOnChange(true);

        // Tạo một kiểu cho các mục trong Spinner
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.city_list)) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(getResources().getColor(android.R.color.white)); // Thay đổi màu chữ thành trắng
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView) view).setTextColor(getResources().getColor(android.R.color.black)); // Thay đổi màu chữ cho danh sách thả xuống
                return view;
            }
        };
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCities.setAdapter(stringArrayAdapter);

        // Xử lý sự kiện khi người dùng chọn thành phố
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = parent.getItemAtPosition(position).toString();
                // Cập nhật tên thành phố hiển thị (nếu cần)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý nếu không có thành phố nào được chọn
            }
        });

        // Xử lý khi người dùng nhấn vào "Thay đổi"
        findViewById(R.id.citySelectorLayout).setOnClickListener(v -> {
            // Logic thay đổi thành phố tại đây
        });
    }

    // Phương thức giả lập dữ liệu rạp gần bạn
    private List<Rap> getNearbyRaps() {
        List<Rap> rapList = new ArrayList<>();
        rapList.add(new Rap("CGV Vĩnh Trung Plaza", "255-257 đường Hùng Vương, Đà Nẵng", R.drawable.cgv,0.5));
        rapList.add(new Rap("Starlight Đà Nẵng", "T4-Tòa nhà Nguyễn Kim, Đà Nẵng", R.drawable.starlight,1));
        rapList.add(new Rap("CGV Vincom Đà Nẵng", "Tầng 4, TTTM Vincom, Đà Nẵng", R.drawable.starlight,2.3));
        return rapList;
    }

    // Implement phương thức onItemClick từ OnItemClickListener
    @Override
    public void onItemClick(Rap rap) {
        // Logic xử lý khi người dùng click vào một rạp
        // Ví dụ: mở một Activity mới hoặc hiển thị thông tin chi tiết
    }
}
