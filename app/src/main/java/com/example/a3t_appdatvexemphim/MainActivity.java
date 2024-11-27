package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.Firebase;

public class MainActivity extends AppCompatActivity {
    TextView fogotPass;
     View mainLayout;
     TextView signUp;
     Button button_login_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main);
        mainLayout.setBackgroundResource(R.drawable.animation_movie);
        fogotPass = findViewById(R.id.textViewForgotPassword);
        button_login_main = findViewById(R.id.button_login_main);
        signUp = findViewById(R.id.textViewSignUp);
         // Ensure this ID matches your layout

        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_login_main.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        });

        signUp.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, formSignIn.class);
            startActivity(intent1);
        });

        fogotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, QuenMK.class);
                startActivity(intent1);
            }
        });
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("message");
        myref.setValue("hello,world");
    }
}
