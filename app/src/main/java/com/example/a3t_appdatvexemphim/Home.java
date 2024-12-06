package com.example.a3t_appdatvexemphim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.RAP.CinemaFragment;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.example.a3t_appdatvexemphim.Video.Video_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private String userId; // Biến lưu userId

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Layout chính của Home

        // Lấy userId từ Intent
        userId = getIntent().getStringExtra("USER_ID");

        // Ánh xạ view
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Hiển thị mặc định TrangChuFragment
        replaceFragment(new TrangChuFragment());

        // Xử lý chọn mục trên BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;
            int itemId = item.getItemId();

            if (itemId == R.id.trangchu) {
                selectedFragment = new TrangChuFragment();
            } else if (itemId == R.id.voucher) {
                selectedFragment = new KhuyenMaiFragment();
            } else if (itemId == R.id.rap) {
                selectedFragment = new CinemaFragment();
            } else {
                // Truyền userId tới KhacFragment
                selectedFragment = KhacFragment.newInstance(userId);
            }

            replaceFragment(selectedFragment);
            return true;
        });

        // Xử lý để không che khuất thanh trạng thái và thanh điều hướng
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Hàm thay thế fragment trong container
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment); // frame_layout là container cho fragment
        fragmentTransaction.addToBackStack(null); // Optional: add to back stack
        fragmentTransaction.commit();
    }
}