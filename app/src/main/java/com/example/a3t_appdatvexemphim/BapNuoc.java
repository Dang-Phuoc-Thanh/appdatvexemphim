package com.example.a3t_appdatvexemphim;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BapNuoc extends AppCompatActivity {
    Button button_tieptuc;
    ImageView tru1,tru2,cong1,cong2;
    EditText soluong1,soluong2,tongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bap_nuoc);
        tru1=findViewById(R.id.tru1);
        tru2=findViewById(R.id.tru2);
        cong1=findViewById(R.id.cong1);
        cong2=findViewById(R.id.cong2);
        soluong1.setText("0");
        soluong2.setText("0");
        tongtien.setText("0");
        tongtien=findViewById(R.id.tongtien);


        button_tieptuc=findViewById(R.id.button_tieptuc);
        button_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BapNuoc.this, ThanhToan.class);
                startActivity(intent);
            }
        });
        tru1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(soluong1.getText().toString());
                if (currentValue > 0) { // Ensure the value doesn't go below 0
                    currentValue--;
                }
                soluong1.setText(String.valueOf(currentValue));
            }
        });
        tru2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(soluong2.getText().toString());
                if (currentValue > 0) { // Ensure the value doesn't go below 0
                    currentValue--;
                }
                soluong2.setText(String.valueOf(currentValue));
            }
        });
        cong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(soluong1.getText().toString());

                currentValue++;

                soluong1.setText(String.valueOf(currentValue));
            }
        });
        cong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(soluong2.getText().toString());

                currentValue++;

                soluong2.setText(String.valueOf(currentValue));
            }
        });
        int ketqua=((Integer.parseInt(soluong1.getText().toString())*99000)+(Integer.parseInt(soluong2.getText().toString())*149000));
        tongtien.setText(String.valueOf(ketqua));





    }

    /**
     * Default constructor for AppCompatActivity. All Activities must have a default constructor
     * for API 27 and lower devices or when using the default
     * {@link AppComponentFactory}.
     */
    public BapNuoc() {
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
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
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
}