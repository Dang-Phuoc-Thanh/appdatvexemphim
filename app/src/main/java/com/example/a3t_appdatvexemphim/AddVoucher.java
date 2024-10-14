package com.example.a3t_appdatvexemphim;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddVoucher extends AppCompatActivity {
    Button button_apdung;
    EditText input_voucher;

    /**
     * Default constructor for AppCompatActivity. All Activities must have a default constructor
     * for API 27 and lower devices or when using the default
     * {@link AppComponentFactory}.
     */
    public AddVoucher() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_voucher);
        button_apdung=findViewById(R.id.button_apdung);
        input_voucher=findViewById(R.id.input_voucher);
        button_apdung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_voucher.getText().toString().equals("VNPAY"))
                {
                    Toast.makeText(AddVoucher.this,"Da them voucher",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(AddVoucher.this, ThanhToan.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(AddVoucher.this,"Ma voucher khong ton tai",Toast.LENGTH_SHORT).show();


            }
        });






    }
}