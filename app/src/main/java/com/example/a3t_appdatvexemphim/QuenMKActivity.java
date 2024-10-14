package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuenMKActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mat_khau);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnKHOIPHUC), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Tìm nút và thiết lập OnClickListener
        Button btnTieptuc = findViewById(R.id.button); // Thay "btnXacThuc" bằng id thực tế của nút
        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi nút được nhấn, chuyển đến XacThucTaiKhoanActivity
                Intent intent = new Intent(QuenMKActivity.this, XacThucTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }
}