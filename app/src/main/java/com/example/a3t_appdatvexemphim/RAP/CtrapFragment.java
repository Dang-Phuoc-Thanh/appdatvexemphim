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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.CommentFilm_Fragment;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.listAdapter;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
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
    private String userId;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            userId = getArguments().getString("USER_ID"); // Lấy userId từ arguments
            Log.d("CtrapFragment", "Received User ID: " + userId);
        }
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
        adapter = new listAdapter(getContext(), phimList, new ArrayList<>(),userId);
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
                    ClassPhim phim = snapshot.getValue(ClassPhim.class);
                    if (phim != null) {
                        String noiDung = phim.NoiDung;
                        if (noiDung == null) {
                            noiDung = "Nội dung không có sẵn huhuhu"; // Default message if Nội dung is null
                        }
                        phimList.add(new dsFILMHH(
                                phim.TenPhim,
                                "Thời lượng: " + phim.ThoiLuong + " Phút",
                                "Khởi chiếu: " + phim.NgayKhoiChieu,
                                "Nội dung: " + noiDung,
                                "Đặt vé",
                                phim.HinhAnh, // Replace with the actual image URL
                                phim.Video, // Replace with the actual trailer URL
                                phim.MaLich,
                                phim.MaPhim
                        ));
                        Log.d(TAG, "Phim received: " + phim.TenPhim);



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
    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}


