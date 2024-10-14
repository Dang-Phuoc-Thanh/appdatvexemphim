package com.example.a3t_appdatvexemphim;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThanhToan extends AppCompatActivity {
    EditText txt_name;
    EditText txt_email;
    EditText txt_phone;
    EditText select_voucher;
    Button button_ThanhToan;
    TextView select_pppt;

    /**
     * Default constructor for AppCompatActivity. All Activities must have a default constructor
     * for API 27 and lower devices or when using the default
     * {@link AppComponentFactory}.
     */
    public ThanhToan() {
        super();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thanh_toan);
        txt_email=findViewById(R.id.txt_email);
        txt_name=findViewById(R.id.txt_name);
        select_pppt =findViewById(R.id.select_pttt);

        txt_phone=findViewById(R.id.txt_phone);
        select_voucher=findViewById(R.id.select_voucher);
        button_ThanhToan=findViewById(R.id.button_ThanhToan);
        button_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_name.getText().toString().isEmpty() ||
                        txt_email.getText().toString().isEmpty() ||
                        txt_phone.getText().toString().isEmpty()) {
                    Toast.makeText(ThanhToan.this, "Vui long nhap thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ThanhToan.this, VeCuaToi.class);
                    startActivity(intent);
                }
            }
        });
        select_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(ThanhToan.this, VoucherHome.class);
                startActivity(intent1);
            }
        });
        select_pppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(ThanhToan.this, PhuongThucThanhToan.class);
                startActivity(intent2);
            }
        });




    }
}