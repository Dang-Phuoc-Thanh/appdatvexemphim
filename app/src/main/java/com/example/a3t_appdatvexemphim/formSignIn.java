package com.example.a3t_appdatvexemphim;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class formSignIn extends AppCompatActivity {

    private EditText hotenEditText, emailEditText, sdtEditText, matkhauEditText, nhaplaiMatkhauEditText, dateEditText;
    private RadioGroup gioiTinhRadioGroup;
    private Button calendarButton, btnSignIn;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sign_in);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("TAIKHOAN");

        // Initialize views
        hotenEditText = findViewById(R.id.HOTEN);
        emailEditText = findViewById(R.id.EMAIL);
        sdtEditText = findViewById(R.id.SDT);
        matkhauEditText = findViewById(R.id.MK);
        nhaplaiMatkhauEditText = findViewById(R.id.nhaplaiMK);
        dateEditText = findViewById(R.id.dateNgaySinh);
        gioiTinhRadioGroup = findViewById(R.id.radioGroupGioiTinh);
        calendarButton = findViewById(R.id.calendarButton);
        btnSignIn = findViewById(R.id.btnDANGKY);

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                formSignIn.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String selectedDate = i2 + "/" + (i1 + 1) + "/" + i;
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }


    private void registerUser() {
        String hoten = hotenEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String sdt = sdtEditText.getText().toString().trim();
        String matkhau = matkhauEditText.getText().toString().trim();
        String nhaplaiMatkhau = nhaplaiMatkhauEditText.getText().toString().trim();
        String ngaySinh = dateEditText.getText().toString().trim();
        int selectedGioiTinhId = gioiTinhRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGioiTinhButton = findViewById(selectedGioiTinhId);
        String gioiTinh = selectedGioiTinhButton != null ? selectedGioiTinhButton.getText().toString() : "";

        if (hoten.isEmpty() || email.isEmpty() || sdt.isEmpty() || matkhau.isEmpty() || nhaplaiMatkhau.isEmpty() || ngaySinh.isEmpty() || selectedGioiTinhId == -1) {
            Toast.makeText(formSignIn.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            Log.w("RegisterUser", "Thông tin không đầy đủ");
            return;
        }

        if (!matkhau.equals(nhaplaiMatkhau)) {
            Toast.makeText(formSignIn.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            Log.w("RegisterUser", "Mật khẩu không khớp");
            return;
        }

        auth.createUserWithEmailAndPassword(email, matkhau)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("RegisterUser", "Tạo tài khoản thành công");
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            Log.d("RegisterUser", "User ID: " + userId);
                            String id = databaseReference.push().getKey(); // Generate unique ID
                            User newUser = new User(id, hoten, email, sdt, matkhau, ngaySinh, gioiTinh);
                            databaseReference.child(userId).setValue(newUser)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Log.d("RegisterUser", "Lưu thông tin người dùng thành công");
                                            Toast.makeText(formSignIn.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Log.e("RegisterUser", "Lưu thông tin người dùng thất bại", task1.getException());
                                            Toast.makeText(formSignIn.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Log.e("RegisterUser", "Đăng ký thất bại", task.getException());
                        Toast.makeText(formSignIn.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public static class User {
        public String id;
        public String hoten;
        public String email;
        public String sdt;
        public String matkhau;
        public String ngaySinh;
        public String gioiTinh;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String id, String hoten, String email, String sdt, String matkhau, String ngaySinh, String gioiTinh) {
            this.id = id;
            this.hoten = hoten;
            this.email = email;
            this.sdt = sdt;
            this.matkhau = matkhau;
            this.ngaySinh = ngaySinh;
            this.gioiTinh = gioiTinh;
        }
    }
}