package com.example.a3t_appdatvexemphim.RAP;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.SuatChieu;
import com.example.a3t_appdatvexemphim.Trangchu.FILM;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CinemaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CinemaFragment extends Fragment  {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialAutoCompleteTextView autoCompleteTextView;
    private Button btn;
    private RecyclerView nearbyRapRecyclerView;
    private RapAdapter rapAdapter;
    private ImageView back;
    private String mParam1;
    private String mParam2;
    private ImageView arrowIcon;
    private List<Rap> rapList;
    private Rap selectedRap;
    private String userId;
    private static final String ARG_USER_ID = "USER_ID"; // Key để truyền userId qua arguments
    private Integer maPhim;

    private DatabaseReference databaseReference;


    public static CinemaFragment newInstance(String userId) {
        CinemaFragment fragment = new CinemaFragment();
        Bundle args = new Bundle();
        args.putString("USER_ID", userId);
        fragment.setArguments(args);
        return fragment;
    }

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
            userId = getArguments().getString("USER_ID");
            Log.d("CinemaFragment", "Received User ID: " + userId);
            maPhim = getArguments().getInt("MaPhim_tuVideo"); // Lấy maPhim từ arguments
            Log.d("CinemaFragment", "Received MaPhim: " + maPhim);
        }
        rapList = new ArrayList<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);


       Bundle args = getArguments();


        final dsFILMHH selectedFilm;
        if (args != null) {
            selectedFilm = (dsFILMHH) args.getSerializable("selectedFilm");
        } else {
            selectedFilm = null;
        }


        autoCompleteTextView = view.findViewById(R.id.inputTV);
        nearbyRapRecyclerView = view.findViewById(R.id.nearbyRapRecyclerView);

        nearbyRapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String thanhPho = (String) parent.getItemAtPosition(position);
                Log.d("CinemaFragment", "City selected: " + thanhPho);
                Snackbar.make(view, "Thành phố đã chọn: " + thanhPho, Snackbar.LENGTH_LONG).show();
                fetchMaTPAndUpdateCinemas(thanhPho);
            }
        });

                rapAdapter = new RapAdapter(new ArrayList<>(), new RapAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Rap rap) {
                        selectedRap = rap;
                        if (selectedFilm != null){
                            // Khi người dùng chọn rạp, chuyển sang SuatChieuFragment
                        SuatChieu suatChieuFragment = new SuatChieu();

                        // Truyền thông tin phim và rạp đã chọn

                        Bundle bundle = new Bundle();
                        if (args != null) {
                            dsFILMHH selectedFilm = (dsFILMHH) args.getSerializable("selectedFilm");
                            bundle.putString("TenPhim", selectedFilm.getName());
                            bundle.putString("NoiDung", selectedFilm.getNoidung());
                            bundle.putString("HinhAnh", selectedFilm.getImageUrl());
                            bundle.putSerializable("selectedFilm", selectedFilm);
                            bundle.putString("USER_ID", userId); // Truyền userId
                        }
                            bundle.putSerializable("rap", selectedRap);
                        suatChieuFragment.setArguments(bundle);

                        // Chuyển sang SuatChieuFragment
                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, suatChieuFragment)
                                .addToBackStack(null)
                                .commit();


                        }else{
                            // Khi người dùng chọn rạp, chuyển sang SuatChieuFragment
                        CtrapFragment ctrapFragment = new CtrapFragment();

                        // Truyền thông tin phim và rạp đã chọn

                        Bundle bundle = new Bundle();

                            bundle.putSerializable("rap", selectedRap);
                            bundle.putString("USER_ID", userId); // Truyền userId
                        ctrapFragment.setArguments(bundle);

                        // Chuyển sang SuatChieuFragment
                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout, ctrapFragment)
                                .addToBackStack(null)
                                .commit();
                        }


                    }
                });

               nearbyRapRecyclerView.setAdapter(rapAdapter);
        fetchCitiesFromFirebase();
                // Fetch dữ liệu rạp từ Firebase
               // fetchCinemasFromFirebase();
        databaseReference = FirebaseDatabase.getInstance().getReference("Raps");
        loadRapData();

        return view;
    }

    private void loadRapData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rapList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Rap rap = dataSnapshot.getValue(Rap.class);
                    if (rap != null) {
                        rapList.add(rap);
                    }
                }
                rapAdapter.updateRapList(rapList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CinemaFragment", "Lỗi khi tải dữ liệu: " + error.getMessage());
            }
        });
    }

    private void navigateToCtrapFragment(Rap selectedRap) {
        // Create a new instance of CtrapFragment
        CtrapFragment ctrapFragment = new CtrapFragment();

        // Create a bundle to pass the selected cinema data
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedRap", selectedRap);
        bundle.putString("USER_ID", userId); // Truyền userId
        ctrapFragment.setArguments(bundle);

        // Navigate to CtrapFragment
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, ctrapFragment)
                .addToBackStack(null)
                .commit();
    }

    private void onRapClick(Rap rap) {
        if (rap == null) {
            Log.e("CinemaFragment", "Rạp được chọn bị null");
            return;
        }

        try {
            SuatChieu suatChieuFragment = new SuatChieu();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Rap", rap); // Rap phải implement Serializable
            bundle.putString("USER_ID", userId); // Truyền userId
            suatChieuFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, suatChieuFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e("CinemaFragment", "Lỗi khi chuyển sang SuatChieuFragment", e);
        }
    }


    private void fetchCitiesFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ThanhPho");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> cityList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String cityName = snapshot.child("TenTP").getValue(String.class);
                    cityList.add(cityName);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, cityList);
                autoCompleteTextView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
                Snackbar.make(nearbyRapRecyclerView, "Không thể tải dữ liệu. Vui lòng thử lại!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void fetchMaTPAndUpdateCinemas(String cityName) {
        DatabaseReference cityRef = FirebaseDatabase.getInstance().getReference("ThanhPho");
        cityRef.orderByChild("TenTP").equalTo(cityName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String maTP = snapshot.child("MaTP").getValue(String.class);
                        fetchCinemasByMaTP(maTP);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
                Snackbar.make(nearbyRapRecyclerView, "Không thể tải dữ liệu. Vui lòng thử lại!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void fetchCinemasByMaTP(String maTP) {
        DatabaseReference rapRef = FirebaseDatabase.getInstance().getReference("RAP");
        rapRef.orderByChild("MaTP").equalTo(maTP).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Rap> rapList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Rap rap = snapshot.getValue(Rap.class);
                    rapList.add(rap);
                }
                rapAdapter.updateRapList(rapList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
                Snackbar.make(nearbyRapRecyclerView, "Không thể tải dữ liệu. Vui lòng thử lại!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
