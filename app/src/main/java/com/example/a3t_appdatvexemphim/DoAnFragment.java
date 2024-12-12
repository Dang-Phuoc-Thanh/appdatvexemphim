package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class DoAnFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<Food> foodList;
    private FoodAdapter foodAdapter;

    public DoAnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_an, container, false);

        // Ánh xạ RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewDoAn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo danh sách và Adapter
        foodList = new ArrayList<>();
        foodAdapter = new FoodAdapter(foodList, getContext());
        recyclerView.setAdapter(foodAdapter);

        // Kết nối đến Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("DOAN");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear(); // Xóa dữ liệu cũ để cập nhật danh sách mới

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Lấy dữ liệu từ Firebase
                    String tenDoAn = dataSnapshot.child("TenDoAn").getValue(String.class);
                    String moTa = dataSnapshot.child("MoTa").getValue(String.class);
                    Long donGia = dataSnapshot.child("DonGia").getValue(Long.class);
                    String hinhAnh = dataSnapshot.child("HinhAnh").getValue(String.class);

                    // Tạo đối tượng Food và thêm vào danh sách
                    Food food = new Food(tenDoAn, moTa, donGia, hinhAnh);
                    foodList.add(food);
                }

                // Thông báo cho Adapter cập nhật dữ liệu
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Không thể tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
