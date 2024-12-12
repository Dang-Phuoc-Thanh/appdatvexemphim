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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.R;
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
        View view = inflater.inflate(R.layout.fragment_cinema, container, false);

        autoCompleteTextView = view.findViewById(R.id.inputTV);
        nearbyRapRecyclerView = view.findViewById(R.id.nearbyRapRecyclerView);

        nearbyRapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rapAdapter = new RapAdapter(new ArrayList<>(), new RapAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Rap rap) {
                CtrapFragment ctrapFragment = CtrapFragment.newInstance(rap);
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, ctrapFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        nearbyRapRecyclerView.setAdapter(rapAdapter);

        fetchCitiesFromFirebase();

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String thanhPho = (String) parent.getItemAtPosition(position);
                Log.d("CinemaFragment", "City selected: " + thanhPho);
                Snackbar.make(view, "Thành phố đã chọn: " + thanhPho, Snackbar.LENGTH_LONG).show();
                fetchMaTPAndUpdateCinemas(thanhPho);
            }
        });

        return view;
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
            }
        });
    }

}
