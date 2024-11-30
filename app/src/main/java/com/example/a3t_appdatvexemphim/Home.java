package com.example.a3t_appdatvexemphim;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.RAP.CinemaFragment;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Ensure you have this layout

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        replaceFragment(new TrangChuFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.trangchu) {
                replaceFragment(new TrangChuFragment());
                return true;
            } else if (itemId == R.id.voucher) {
                replaceFragment(new VoucherFragment());
                return true;
            } else if (itemId == R.id.rap) {
                replaceFragment(new CinemaFragment());
                return true;
            }
            replaceFragment(new ImageFragment());
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment); // Ensure this ID matches your layout
        fragmentTransaction.addToBackStack(null); // Optional: add to back stack
        fragmentTransaction.commit();
    }


}
