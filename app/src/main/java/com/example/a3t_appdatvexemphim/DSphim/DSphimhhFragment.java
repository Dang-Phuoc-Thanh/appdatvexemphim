package com.example.a3t_appdatvexemphim.DSphim;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a3t_appdatvexemphim.BapNuocFragment;
import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.listAdapter;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.example.a3t_appdatvexemphim.VoucherFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DSphimhhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DSphimhhFragment extends Fragment {
    private ListView DSphim;
    private ArrayList<dsFILMHH> list;
    private listAdapter adapter;
    private ImageView back;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DSphimhhFragment() {
        // Required empty public constructor
    }

    public static DSphimhhFragment newInstance(String param1, String param2) {
        DSphimhhFragment fragment = new DSphimhhFragment();
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
        View view = inflater.inflate(R.layout.fragment_d_sphimhh, container, false);

        // Initialize the ListView
        DSphim = view.findViewById(R.id.listview);

        // Initialize the list and add data
        list = new ArrayList<>();
        list.add(new dsFILMHH("SPY x FAMILY", "Thời lượng: 180 Phút", "Khởi chiếu: 20/09/2024", "Đặt vé", R.drawable.phim6));
        list.add(new dsFILMHH("Xác ướp - Cuộc phiêu lưu đến LonDon", "Thời lượng: 90 Phút", "Khởi chiếu: 20/09/2024", "Đặt vé", R.drawable.phh11));
        list.add(new dsFILMHH("Mèo béo siêu đẳng", "Thời lượng: 150 Phút", "Khởi chiếu: 20/09/2024", "Đặt vé", R.drawable.phh2));
        list.add(new dsFILMHH("Bản giao hưởng địa cầu", "Thời lượng: 90 Phút", "Khởi chiếu: 20/09/2024", "Đặt vé", R.drawable.phh5));
        list.add(new dsFILMHH("Truyền thuyết nhẫn thuật ninja", "Thời lượng: 180 Phút", "Khởi chiếu: 20/09/2024", "Đặt vé", R.drawable.phh6));

        // Set the adapter
        adapter = new listAdapter(getActivity(), list);
        DSphim.setAdapter(adapter);


        // Set the OnItemClickListener for the ListView
        back=view.findViewById(R.id.ic_quaylai);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new TrangChuFragment(); // Replace with your target fragment

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
