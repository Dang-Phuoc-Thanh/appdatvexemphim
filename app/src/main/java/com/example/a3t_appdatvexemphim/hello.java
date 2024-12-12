package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hello extends AppCompatActivity {
    List<ClassPhim> DsPhim = new ArrayList<>();
    Map<Long, Long> phimTheLoaiMap = new HashMap<>();
    DatabaseReference mData;
    TextView txt_Phim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        txt_Phim = findViewById(R.id.txt_Phim);
        mData = FirebaseDatabase.getInstance().getReference();

        // Lấy dữ liệu thể loại phim
        mData.child("PHIM_THELOAIPHIM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phimTheLoaiMap.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Long maPhim = snapshot.child("MaPhim").getValue(Long.class);
                        Long maTheLoaiPhim = snapshot.child("MaTheLoaiPhim").getValue(Long.class);
                        if (maPhim != null && maTheLoaiPhim != null) {
                            phimTheLoaiMap.put(maPhim, maTheLoaiPhim);
                        }
                    } catch (Exception e) {
                        Log.e("PhimTheLoai", "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
                    }
                }
                // Lấy dữ liệu phim sau khi có thông tin thể loại phim
                getPhimData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(hello.this, "Lỗi khi tải dữ liệu thể loại phim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPhimData() {
        mData.child("PHIM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DsPhim.clear();
                StringBuilder phimDisplay = new StringBuilder();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Long maPhim = snapshot.child("MaPhim").getValue(Long.class);
                        Long maTheLoaiPhim = phimTheLoaiMap.get(maPhim);

                        if (maTheLoaiPhim != null && maTheLoaiPhim.equals(6L)) { // Hoạt hình
                            ClassPhim phim = snapshot.getValue(ClassPhim.class);
                            if (phim != null) {
                                DsPhim.add(phim);
                                phimDisplay.append(phim.TenPhim).append("\n");
                                Log.d("PhimHoatHinh", "Tên phim: " + phim.TenPhim + ", Đạo diễn: " + phim.DaoDien);
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Phim", "Error parsing data", e);
                    }
                }

                // Update UI
                if (!DsPhim.isEmpty()) {
                    ClassPhim phimDauTien = DsPhim.get(0);
                    String thongTinPhim = "Tên phim: " + phimDauTien.TenPhim + "\nĐạo diễn: " + phimDauTien.DaoDien
                            + "\nNgày khởi chiếu: " + phimDauTien.NgayKhoiChieu + "\nNgày kết thúc: " + phimDauTien.NgayKetThuc;
                    txt_Phim.setText(thongTinPhim);
                    Toast.makeText(hello.this, thongTinPhim, Toast.LENGTH_LONG).show();
                } else {
                    txt_Phim.setText("Không có phim hoạt hình nào.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(hello.this, "Lỗi khi tải dữ liệu phim", Toast.LENGTH_SHORT).show();
            }
        });
    }
}