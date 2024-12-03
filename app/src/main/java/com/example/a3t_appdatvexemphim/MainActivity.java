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
    private TextView forgotPassword, signUp;
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
    }



    // Kiểm tra thông tin đăng nhập với Firebase
    private void checkLogin(String email, String password) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("TAIKHOAN");
        database.orderByChild("Email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Lấy giá trị Email và MatKhau
                        String dbEmail = snapshot.child("Email").getValue(String.class);
                        Object dbPasswordObj = snapshot.child("MatKhau").getValue();

                        // Kiểm tra email khớp
                        if (dbEmail != null && dbEmail.equals(email)) {
                            if (dbPasswordObj instanceof Long) {
                                // Nếu MatKhau là kiểu Long, chuyển sang String
                                String dbPassword = String.valueOf(dbPasswordObj);
                                if (dbPassword.equals(password)) {
                                    navigateToHome();
                                    return;
                                }
                            } else if (dbPasswordObj instanceof String) {
                                // Nếu MatKhau là kiểu String (dự phòng)
                                String dbPassword = (String) dbPasswordObj;
                                if (dbPassword.equals(password)) {
                                    navigateToHome();
                                    return;
                                }
                            }
                            Toast.makeText(MainActivity.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Toast.makeText(MainActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            fetchUserData(userId);
                        }
                    } else {
                        // Log the error message
                        Log.e("AuthError", "Authentication failed: " + task.getException().getMessage());
                        Toast.makeText(MainActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToHome() {
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish(); // Optional: finish MainActivity if you don't want to allow the user to go back to the login screen
    }

    private void fetchUserData(String userId) {
        // Lấy tham chiếu tới người dùng trong Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TAIKHOAN").child(userId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Lấy thông tin từ Firebase
                    String hoten = dataSnapshot.child("hoten").getValue(String.class);
                    String gioiTinh = dataSnapshot.child("GioiTinh").getValue(String.class);
                    String email = dataSnapshot.child("Email").getValue(String.class);
                    String sdt = dataSnapshot.child("SDT").getValue(String.class);

                    // Log dữ liệu để kiểm tra
                    Log.d("UserData", "Hoten: " + hoten);
                    Log.d("UserData", "GioiTinh: " + gioiTinh);
                    Log.d("UserData", "Email: " + email);
                    Log.d("UserData", "SDT: " + sdt);

                    // Truyền dữ liệu tới Fragment
                    TaiKhoan fragment = TaiKhoan.newInstance(hoten, gioiTinh, email, sdt);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment)  // Đảm bảo rằng R.id.frame_layout là ID của FrameLayout trong activity_main.xml
                            .commit();
                } else {
                    // Nếu không tìm thấy người dùng
                    Toast.makeText(MainActivity.this, "User not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                Toast.makeText(MainActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
