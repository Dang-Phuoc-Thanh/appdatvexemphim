package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.DSphimhhFragment;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
import com.example.a3t_appdatvexemphim.Video.Video_Fragment;
import com.example.a3t_appdatvexemphim.danhgia.BinhLuan;
import com.example.a3t_appdatvexemphim.danhgia.BinhLuanAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentFilm_Fragment extends Fragment {

    private ImageView back;
    private ImageView imgFilm;
    private TextView nameFilm;
    private TextView contentFilm;
    private String userId;
    private int MaPhim;

    public CommentFilm_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString("USER_ID");
            MaPhim = getArguments().getInt("MaPhim");
            Log.d("CommentFilm_Fragment", "Received User ID: " + userId + " MaPhim: " + MaPhim);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_film_, container, false);

        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
        ArrayList<ClassPhim> danhsachphim = getArguments().getParcelableArrayList("danhsachphim");

        imgFilm = view.findViewById(R.id.img_film);
        nameFilm = view.findViewById(R.id.name_film);
        contentFilm = view.findViewById(R.id.Content_film);
        back = view.findViewById(R.id.back);
        TextView trailer = view.findViewById(R.id.trailer);

        // Thiết lập dữ liệu cho các thành phần giao diện
        if (selectedFilm != null) {
            Glide.with(this).load(selectedFilm.getImageUrl()).into(imgFilm);
            nameFilm.setText(selectedFilm.getName());
            contentFilm.setText(selectedFilm.getNoidung()); // Giả sử bạn muốn hiển thị thông tin đặt vé
        }

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video_Fragment videoFragment = new Video_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", selectedFilm);
                bundle.putString("USER_ID", userId);
                videoFragment.setArguments(bundle);

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, videoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new DSphimhhFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("danhsachphim", danhsachphim);
                bundle.putString("USER_ID", userId);
                newFragment.setArguments(bundle);

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.nearbyBINHLUANRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TextView emptyView = view.findViewById(R.id.empty_view); // Tham chiếu đến TextView thông báo

        fetchComments(recyclerView, emptyView);
        return view;
    }

    private void fetchComments(RecyclerView recyclerView, TextView emptyView) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference commentsRef = database.getReference("BINHLUAN");
        DatabaseReference usersRef = database.getReference("TAIKHOAN");

        commentsRef.orderByChild("maPhim").equalTo(String.valueOf(MaPhim)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<BinhLuan> binhLuanList = new ArrayList<>();
                Map<String, String> userNames = new HashMap<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BinhLuan binhLuan = snapshot.getValue(BinhLuan.class);
                    if (binhLuan != null) {
                        binhLuanList.add(binhLuan);
                    }
                }

                if (binhLuanList.isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    for (BinhLuan binhLuan : binhLuanList) {
                        if (binhLuan.getId() != null) {
                            usersRef.child(binhLuan.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String hoTen = dataSnapshot.child("hoten").getValue(String.class);
                                    userNames.put(binhLuan.getId(), hoTen);

                                    if (userNames.size() == binhLuanList.size()) {
                                        BinhLuanAdapter adapter = new BinhLuanAdapter(binhLuanList, userNames);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.e("CommentFilm_Fragment", "Failed to read user data", databaseError.toException());
                                }
                            });
                        } else {
                            Log.e("CommentFilm_Fragment", "BinhLuan id is null");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("CommentFilm_Fragment", "Failed to read comments", databaseError.toException());
            }
        });
    }
}