package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;

import java.util.ArrayList;

public class DatGheFragment extends Fragment {
    private ImageView back;
    private Button datghe;
    private boolean isHighlighted = false;
    private ArrayList<String> danhSachGheDuocChon = new ArrayList<>();

    // Danh sách ID của các ghế
    private int[] danhSachGhe = {
            R.id.idghe_A01, R.id.idghe_A02, R.id.idghe_A03, R.id.idghe_A04,
            R.id.idghe_B01, R.id.idghe_B02, R.id.idghe_B03, R.id.idghe_B04,
            R.id.idghe_C01, R.id.idghe_C02, R.id.idghe_C03, R.id.idghe_C04,
            R.id.idghe_D01, R.id.idghe_D02, R.id.idghe_D03, R.id.idghe_D04
    };

    public DatGheFragment() {
        // Required empty public constructor
    }

    public static DatGheFragment newInstance(String param1, String param2) {
        DatGheFragment fragment = new DatGheFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dat_ghe, container, false);

        back = view.findViewById(R.id.imageView2);
        datghe = view.findViewById(R.id.button_datghe);

        // Gán sự kiện cho nút "Đặt Ghế"
        datghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("danhSachGheDuocChon", danhSachGheDuocChon);
                if (getArguments() != null) {
                    dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");
                    bundle.putSerializable("selectedFilm", selectedFilm); // Truyền selectedFilm vào Bundle
                }
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                BapNuocFragment bapNuocFragment = new BapNuocFragment();
                bapNuocFragment.setArguments(bundle); // Truyền danh sách ghế đã chọn qua fragment mới
                transaction.replace(R.id.frame_layout, bapNuocFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Gán sự kiện quay lại
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, new SuatChieu());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Gán sự kiện click cho các ghế
        for (int idGhe : danhSachGhe) {
            Button ghe = view.findViewById(idGhe);
            ghe.setOnClickListener(new View.OnClickListener() {
                boolean isHighlighted = false; // Trạng thái ban đầu của ghế

                @Override
                public void onClick(View v) {
                    if (isHighlighted) {
                        ghe.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                        danhSachGheDuocChon.remove(getResources().getResourceEntryName(idGhe)); // Bỏ ghế khỏi danh sách
                    } else {
                        ghe.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu vàng
                        danhSachGheDuocChon.add(getResources().getResourceEntryName(idGhe)); // Thêm ghế vào danh sách
                    }
                    isHighlighted = !isHighlighted;

                    Log.d("DanhSachGhe", "Ghế được chọn: " + danhSachGheDuocChon.toString());
                }
            });
        }

        return view;
    }
}
