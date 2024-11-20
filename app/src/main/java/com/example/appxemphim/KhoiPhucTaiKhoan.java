package com.example.appxemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KhoiPhucTaiKhoan extends AppCompatActivity {
    private Button btn_khoiphuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khoi_phuc_tai_khoan);

        btn_khoiphuc = findViewById(R.id.button2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_khoiphuc.setOnClickListener(view -> {
            Intent intent1 = new Intent(KhoiPhucTaiKhoan.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}
