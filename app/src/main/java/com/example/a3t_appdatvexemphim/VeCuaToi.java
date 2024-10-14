package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class VeCuaToi extends AppCompatActivity {
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ve_cua_toi);

        final ConstraintLayout moreContent = findViewById(R.id.more_content);
        final TextView showMoreButton = findViewById(R.id.show_more_button);

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                  moreContent.setVisibility(View.GONE);
                    showMoreButton.setText("Xem chi tiết");
                } else {
                    moreContent.setVisibility(View.VISIBLE);
                    showMoreButton.setText("Thu gọn");
                }
                isExpanded = !isExpanded;
            }
        });
    }
}
