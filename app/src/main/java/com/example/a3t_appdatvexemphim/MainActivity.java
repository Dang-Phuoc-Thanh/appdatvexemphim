package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView signUp;
    Button button_login_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_login_main=findViewById(R.id.button_login_main);
        signUp = findViewById(R.id.textViewSignUp);
        imageView = findViewById(R.id.myImageView);
        imageView.setBackgroundResource(R.drawable.animation_movie);
        AnimationDrawable myAnim = (AnimationDrawable) imageView.getBackground();
        myAnim.start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeCuaToi.class);
                startActivity(intent);
            }
        });
        button_login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, BapNuoc.class);
                startActivity(intent1);
            }
        });

    }
}