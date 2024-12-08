package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.graphics.drawable.AnimationDrawable;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button buttonLogin;
    private TextView forgotPassword, signUp, tvdsthanhvien;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thiết lập hình nền động
        mainLayout = findViewById(R.id.main);
        mainLayout.setBackgroundResource(R.drawable.animation_movie);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        // Ánh xạ các view
        emailEditText = findViewById(R.id.txEmail);
        passwordEditText = findViewById(R.id.txMatKhau);
        buttonLogin = findViewById(R.id.button_login_main);
        forgotPassword = findViewById(R.id.textViewForgotPassword);
        signUp = findViewById(R.id.textViewSignUp);
        tvdsthanhvien = findViewById(R.id.tvdsthanhvien);

        // Cấu hình Insets để tránh che khuất thanh trạng thái và điều hướng
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Xử lý sự kiện login
        buttonLogin.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập Email và Mật khẩu", Toast.LENGTH_SHORT).show();
            } else {
                checkLogin(email, password);
            }
        });

        // Xử lý chuyển đến màn hình đăng ký
        signUp.setOnClickListener(view -> {
            // Thêm xử lý chuyển màn hình nếu cần
            Intent intent = new Intent(MainActivity.this, formSignIn.class);
            startActivity(intent);
        });

        // Xử lý quên mật khẩu
        forgotPassword.setOnClickListener(view -> {
            // Thêm xử lý chuyển màn hình nếu cần
            Intent intent = new Intent(MainActivity.this, QuenMK.class);
            startActivity(intent);
        });

        tvdsthanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, dsthanhvien.class);
                startActivity(intent);
            }
        });

    }

    private void navigateToHome(String userId) {
        Intent intent = new Intent(MainActivity.this, Home.class);
        intent.putExtra("USER_ID", userId); // Truyền userId qua Intent
        startActivity(intent);
        finish(); // Kết thúc MainActivity nếu không cần quay lại
    }

    private void fetchUserData(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("TAIKHOAN").child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Chuyển đến Fragment TaiKhoan và truyền userId

                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy thông tin tài khoản!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối cơ sở dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });
    }





    // Kiểm tra thông tin đăng nhập với Firebase
    private void checkLogin(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid(); // Lấy userId
                            Log.d("MainActivity", "Đăng nhập thành công với userId: " + userId);
                            navigateToHome(userId); // Chuyển tới TrangChu
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại. Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}