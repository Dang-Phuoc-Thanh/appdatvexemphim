package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.DSphim.DSphimhhFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFilm_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFilm_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView back;

    public CommentFilm_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentFilm_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentFilm_Fragment newInstance(String param1, String param2) {
        CommentFilm_Fragment fragment = new CommentFilm_Fragment();
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

        View view = inflater.inflate(R.layout.fragment_comment_film_, container, false);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment newFragment = new DSphimhhFragment(); // Replace with your target fragment

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