package com.example.a3t_appdatvexemphim;

import static android.content.ContentValues.TAG;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.THONGTINPHIM.TTPhimFragment;
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.example.a3t_appdatvexemphim.danhgia.DanhGiaFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VeCuaToiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VeCuaToiFragment extends Fragment {

    private TextView comment_film, namefilm, nameghe;
    private String selectedFilm;
    private ArrayList<String> selectedSeats;
    private ImageView back,image;
    private String userId;

    private boolean isExpanded = false;

    public VeCuaToiFragment() {
        // Required empty public constructor
    }

    public static VeCuaToiFragment newInstance(Bundle bundle) {
        VeCuaToiFragment fragment = new VeCuaToiFragment();
        fragment.setArguments(bundle); // Pass the bundle to the fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            userId = getArguments().getString("USER_ID"); // Lấy userId từ arguments
            Log.d("VeCuaToiFragment", "Received User ID: " + userId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ve_cua_toi, container, false);
        image=view.findViewById(R.id.imageView5);
        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
        ArrayList<ClassPhim> danhsachphim = getArguments().getParcelableArrayList("danhsachphim");
        Map<String,String>seatmap=new HashMap<>();
        seatmap.put("idghe_A01","Ghế A01");
        seatmap.put("idghe_A02","Ghế A02");
        seatmap.put("idghe_A03","Ghế A03");
        seatmap.put("idghe_A04","Ghế A04");
        seatmap.put("idghe_B01","Ghế B01");
        seatmap.put("idghe_B02","Ghế B02");
        seatmap.put("idghe_B03","Ghế B03");
        seatmap.put("idghe_B04","Ghế B04");
        seatmap.put("idghe_C01","Ghế C01");
        seatmap.put("idghe_C02","Ghế C02");
        seatmap.put("idghe_C03","Ghế C03");
        seatmap.put("idghe_C04","Ghế C04");
        seatmap.put("idghe_D01","Ghế D01");
        seatmap.put("idghe_D02","Ghế D02");
        seatmap.put("idghe_D03","Ghế D03");
        seatmap.put("idghe_D04","Ghế D04");
        seatmap.put("idghe_E01","Ghế E01");
        seatmap.put("idghe_E02","Ghế E02");
        seatmap.put("idghe_E03","Ghế E03");
        seatmap.put("idghe_E04","Ghế E04");


        // Initialize TextViews
        namefilm = view.findViewById(R.id.tenfilm);
        nameghe = view.findViewById(R.id.tenghe);

        // Retrieve the data from the bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Extract the film name and seats from the bundle
            if (selectedFilm != null) {
                Glide.with(this).load(selectedFilm.getImageUrl()).into(image);
                namefilm.setText(selectedFilm.getName());
            }

            selectedSeats = bundle.getStringArrayList("danhSachGheDuocChon");

            // Set the extracted data to TextViews

            if (selectedSeats != null && !selectedSeats.isEmpty()) {
                List<String> seatNames = new ArrayList<>();
                for (String seatId : selectedSeats) {
                    // Chuyển ID ghế thành tên ghế
                    String seatName = seatmap.getOrDefault(seatId, "Ghế không xác định");
                    seatNames.add(seatName);
                }
                nameghe.setText(String.join(", ", seatNames)); // Nối tên các ghế với dấu phẩy
            } else {
                nameghe.setText("No seats selected");
            }
        }

        // Handling the "Show More" button
        final ConstraintLayout moreContent = view.findViewById(R.id.more_content);
        final TextView showMoreButton = view.findViewById(R.id.show_more_button);
        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    moreContent.setVisibility(View.GONE);
                    showMoreButton.setText("Xem chi tiết");
                } else {
                    moreContent.setVisibility(View.VISIBLE);
                    showMoreButton.setText("Thu gọn");
                }
                isExpanded = !isExpanded;
            }
        });

        // Handling the comment click to open the review fragment
        comment_film = view.findViewById(R.id.comment_film);
        comment_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedFilm != null) {
                    Log.d(TAG, "selectedFilm is not null");
                    if (selectedFilm.getMaPhim() != null) {
                        Log.d(TAG, "MaPhim is not null: " + selectedFilm.getMaPhim());
                        Fragment newFragment = new DanhGiaFragment();

                        // Create a Bundle to hold the arguments
                        Bundle args = new Bundle();
                        args.putString("TenPhim1", selectedFilm.getName());
                        args.putString("HinhAnh1", selectedFilm.getImageUrl());
                        args.putInt("MaPhim", selectedFilm.getMaPhim()); // Ensure this value is correct
                        args.putString("USER_ID", userId); // Pass userId
                        // Set the arguments on the new fragment
                        newFragment.setArguments(args);

                        // Replace the current fragment with the new fragment
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        Log.e(TAG, "MaPhim is null");
                        Toast.makeText(getContext(), "MaPhim không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "selectedFilm is null");
                    Toast.makeText(getContext(), "Phim không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Handling the back button click
        back = view.findViewById(R.id.imageView4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backpage();
            }
        });

        return view;
    }

    public void backpage() {
        FragmentManager fragmentManager = getParentFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Go back to the previous fragment without refreshing
        }
    }
}