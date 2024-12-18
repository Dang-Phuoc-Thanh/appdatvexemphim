package com.example.a3t_appdatvexemphim.RAP;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.listAdapter;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.databinding.FragmentCtrapBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CtrapFragment extends Fragment {
    private static final String TAG = "CtrapFragment";
    private FragmentCtrapBinding binding;
    private Rap selectedRap;
    private ArrayList<LichChieu> lichChieuList;
    private DayAdapter dayAdapter;
    private ArrayList<dsFILMHH> phimList;
    private listAdapter adapter;

    public CtrapFragment() {
        // Required empty public constructor
    }

    public static CtrapFragment newInstance(Rap rap) {
        CtrapFragment fragment = new CtrapFragment();
        Bundle args = new Bundle();
        args.putSerializable("rap", rap); // Assuming Rap implements Serializable
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the binding object
        binding = FragmentCtrapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Get data from Bundle
        if (getArguments() != null) {
            selectedRap = (Rap) getArguments().getSerializable("rap");
            if (selectedRap != null) {
                Toast.makeText(getContext(), "Đã nhận được rạp: " + selectedRap.getTenRap(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Không nhận được dữ liệu rạp", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Không nhận được đối số truyền vào", Toast.LENGTH_SHORT).show();
        }

        // Display rap information
        if (selectedRap != null) {
            TextView textView = binding.tenrap;
            textView.setText(selectedRap.getTenRap());
        }

        // Set click listener for back button
        binding.icQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pop the back stack to return to the previous fragment
                getParentFragmentManager().popBackStack();
            }
        });

        // Initialize the ListView and adapter
        ListView listView = binding.listview;
        phimList = new ArrayList<>();
        adapter = new listAdapter(getContext(), phimList, new ArrayList<>());
        listView.setAdapter(adapter);

        // Fetch movies by MaRap
        if (selectedRap != null && selectedRap.getMaLich() != null) {
            fetchMoviesByMaLich(selectedRap.getMaLich());
        } else {
            Log.e(TAG, "MaLich is null");
        }

        // Initialize RecyclerView for LichChieu
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Initialize the list and adapter for days
        lichChieuList = new ArrayList<>();
        dayAdapter = new DayAdapter(lichChieuList, new DayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LichChieu lichChieu) {
                fetchMoviesByMaLich(lichChieu.getMaLich());
            }
        });
        recyclerView.setAdapter(dayAdapter);

        // Fetch LichChieu data from Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("LichChieu");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lichChieuList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LichChieu lichChieu = snapshot.getValue(LichChieu.class);
                    if (lichChieu != null) {
                        lichChieuList.add(lichChieu);
                    } else {
                        Log.d(TAG, "LichChieu is null");
                    }
                }
                dayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error: " + databaseError.getMessage());
            }
        });

        return view;
    }

    private void fetchMoviesByMaLich(String maLich) {
        if (maLich == null || maLich.isEmpty()) {
            Log.e(TAG, "MaLich is null or empty");
            return;
        }

        DatabaseReference phimRef = FirebaseDatabase.getInstance().getReference("PHIM");
        phimRef.orderByChild("MaLich").equalTo(maLich).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phimList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    dsFILMHH phim = snapshot.getValue(dsFILMHH.class);
                    if (phim != null) {
                        String noiDung = phim.getNoidung();
                        if (noiDung == null) {
                            noiDung = "Nội dung không có sẵn"; // Default message if Nội dung is null
                        }
                        phimList.add(new dsFILMHH(
                                phim.getTenPhim(),
                                "Thời lượng: " + phim.getThoiLuong() + " Phút",
                                "Khởi chiếu: " + phim.getNgayKhoiChieu(),
                                "Nội dung: " + noiDung,
                                "Đặt vé",
                                phim.getHinhAnh(), // Replace with the actual image URL
                                phim.getVideo(), // Replace with the actual trailer URL
                                phim.getMaLich()
                        ));
                        Log.d(TAG, "Phim received: " + phim.getTenPhim());
                    } else {
                        Log.d(TAG, "Phim is null");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error: " + databaseError.getMessage());
            }
        });
    }
}


