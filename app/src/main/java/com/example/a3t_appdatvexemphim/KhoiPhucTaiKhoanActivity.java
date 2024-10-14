package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KhoiPhucTaiKhoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khoi_phuc_tai_khoan);

        // Xử lý sự kiện nhấp vào imageView4
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về XacThucTaiKhoanActivity
                Intent intent = new Intent(KhoiPhucTaiKhoanActivity.this, XacThucTaiKhoanActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại nếu bạn không muốn quay lại
            }
        });

        // Thiết lập WindowInsets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnKHOIPHUC), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
