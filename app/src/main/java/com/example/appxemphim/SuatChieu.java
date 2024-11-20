package com.example.appxemphim;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appxemphim.DSphim.DSphimhhFragment;
import com.example.appxemphim.RAP.Rap;
import com.example.appxemphim.RAP.RapAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuatChieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuatChieu extends Fragment {
    CardView rapchieu1;
    private ImageView imageView2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SuatChieu() {
        // Required empty public constructor
    }

    public static SuatChieu newInstance(String param1, String param2) {
        SuatChieu fragment = new SuatChieu();
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
        View view = inflater.inflate(R.layout.fragment_suat_chieu, container, false);

        // Initialize the CardView
        rapchieu1 = view.findViewById(R.id.rap1);

        // Set the OnClickListener for the CardView
        rapchieu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new DatGheFragment()); // Ensure this ID matches the FrameLayout ID in your activity
                transaction.addToBackStack(null);  // Add to backstack to allow navigation back
                transaction.commit();
            }
        });
        imageView2=view.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newFragment = new DSphimhhFragment();

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
