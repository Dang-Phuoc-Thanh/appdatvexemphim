package com.example.a3t_appdatvexemphim.RAP;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CinemaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinemaFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialAutoCompleteTextView autoCompleteTextView;
    private Button btn;
    private RecyclerView nearbyRapRecyclerView;
    private RapAdapter rapAdapter;
    private ImageView back;
    private String mParam1;
    private String mParam2;

    public CinemaFragment() {
        // Required empty public constructor
    }

    public static CinemaFragment newInstance(String param1, String param2) {
        CinemaFragment fragment = new CinemaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);

        btn = view.findViewById(R.id.btn_chontinh);
        autoCompleteTextView = view.findViewById(R.id.inputTV);
        nearbyRapRecyclerView = view.findViewById(R.id.nearbyRapRecyclerView);

        // Set up RecyclerView
        nearbyRapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rapAdapter = new RapAdapter(new ArrayList<>(), new RapAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Rap rap) {
                // Handle item click
                CtrapFragment ctrapFragment = CtrapFragment.newInstance(rap);
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, ctrapFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        nearbyRapRecyclerView.setAdapter(rapAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String thanhPho = autoCompleteTextView.getText().toString();
                Log.d("CinemaFragment", "Button clicked");
                Snackbar.make(view, "Thành phố đã chọn: " + thanhPho, Snackbar.LENGTH_LONG).show();

                // Update the list of cinemas based on the selected city
                List<Rap> nearbyRaps = getNearbyRapsForCity(thanhPho);
                rapAdapter.updateRapList(nearbyRaps);
            }
        });

        return view;
    }

    // Method to get the list of cinemas based on the selected city
    private List<Rap> getNearbyRapsForCity(String city) {
        List<Rap> rapList = new ArrayList<>();

        if (city.equals("Đà Nẵng")) {
            rapList.add(new Rap("CGV Vĩnh Trung Plaza", "255-257 đường Hùng Vương, Đà Nẵng", R.drawable.cgv, 1.2));
            rapList.add(new Rap("Starlight Đà Nẵng", "T4-Tòa nhà Nguyễn Kim, Đà Nẵng", R.drawable.starlight, 0.8));
        } else if (city.equals("Hà Nội")) {
            rapList.add(new Rap("CGV Vincom Center", "191 Bà Triệu, Hà Nội", R.drawable.cgv, 2.5));
            rapList.add(new Rap("Galaxy Nguyễn Du", "116 Nguyễn Du, Hà Nội", R.drawable.galaxy, 3.0));
            rapList.add(new Rap("BHD Star Phạm Hùng", "Tầng 3, Vincom Center Phạm Hùng, Hà Nội", R.drawable.rio, 1.8));
        } else if (city.equals("Hồ Chí Minh")) {
            rapList.add(new Rap("CGV Vincom Đồng Khởi", "171 Đồng Khởi, Hồ Chí Minh", R.drawable.cgv, 5.0));
            rapList.add(new Rap("Galaxy Nguyễn Huệ", "85 Nguyễn Huệ, Hồ Chí Minh", R.drawable.galaxy, 4.5));
        } else if (city.equals("Hải Phòng")) {
            rapList.add(new Rap("CGV Hải Phòng", "Số 2, Lê Hồng Phong, Hải Phòng", R.drawable.cgv, 2.0));
            rapList.add(new Rap("BHD Star Hải Phòng", "Thành phố Hải Phòng", R.drawable.rio, 1.5));
        } else if (city.equals("Quảng Trị")) {
            rapList.add(new Rap("Rạp Quảng Trị", "Đường Hùng Vương, Quảng Trị", R.drawable.cgv, 2.0));
            rapList.add(new Rap("Galaxy Quảng Trị", "Đường Trần Hưng Đạo, Quảng Trị", R.drawable.galaxy, 2.5));
        }

        return rapList;
    }
}
