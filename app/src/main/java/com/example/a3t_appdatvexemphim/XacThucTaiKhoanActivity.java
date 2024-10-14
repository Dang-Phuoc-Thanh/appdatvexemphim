package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XacThucTaiKhoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xac_thuc_tai_khoan);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnKHOIPHUC), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Xử lý sự kiện nhấn cho ImageView
        ImageView imageViewBack = findViewById(R.id.imageView4);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại QuenMKActivity
                Intent intent = new Intent(XacThucTaiKhoanActivity.this, QuenMKActivity.class);
                startActivity(intent);
                finish(); // Có thể gọi finish() nếu không muốn quay lại
            }
        });

        // Xử lý sự kiện nhấn cho TextView
        TextView textViewResendCode = findViewById(R.id.textView15);
        textViewResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại QuenMKActivity
                Intent intent = new Intent(XacThucTaiKhoanActivity.this, QuenMKActivity.class);
                startActivity(intent);
                finish(); // Có thể gọi finish() nếu không muốn quay lại
            }
        });

        // Xử lý sự kiện nhấn cho Button
        Button btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang KhoiPhucTaiKhoanActivity
                Intent intent = new Intent(XacThucTaiKhoanActivity.this, KhoiPhucTaiKhoanActivity.class);
                startActivity(intent);
                finish(); // Có thể gọi finish() nếu không muốn quay lại
            }
        });
    }
}
