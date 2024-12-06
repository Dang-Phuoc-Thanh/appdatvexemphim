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
import com.example.a3t_appdatvexemphim.Trangchu.ClassPhim;
import com.example.a3t_appdatvexemphim.Trangchu.TrangChuFragment;
import com.example.a3t_appdatvexemphim.VoucherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DSphimhhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DSphimhhFragment extends Fragment {
    private ListView DSphim;
    private ArrayList<dsFILMHH> list;
    private listAdapter adapter;
    private ImageView butback;

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
        View view = inflater.inflate(R.layout.fragment_d_sphimhh, container, false);
        butback=view.findViewById(R.id.back);
        // Nhận danh sách phim từ Bundle
        Bundle myBundle = getArguments();
        ArrayList<ClassPhim> danhsachphim = new ArrayList<>();
        if (myBundle != null) {
            danhsachphim = myBundle.getParcelableArrayList("danhsachphim");
        }

        // Initialize the ListView
        ListView DSphim = view.findViewById(R.id.listview);

        // Initialize the list and add data
        ArrayList<dsFILMHH> list = new ArrayList<>();
        if (danhsachphim != null) {
            for (ClassPhim phim : danhsachphim) {
                list.add(new dsFILMHH(
                        phim.TenPhim,
                        "Thời lượng: " + phim.ThoiLuong + " Phút",
                        "Khởi chiếu: " + phim.NgayKhoiChieu, "Nội dung: " + phim.NoiDung,
                        "Đặt vé",
                        phim.HinhAnh, // Replace with the actual image URL
                        phim.Video// Replace with the actual trailer URL

                ));
            }
        }

        // Khởi tạo adapter với đủ ba tham số
        listAdapter adapter = new listAdapter(getContext(), list, danhsachphim);
        DSphim.setAdapter(adapter);
        butback.setOnClickListener(new View.OnClickListener() {
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
            fragmentManager.popBackStack(); // Quay lại Fragment trước đó mà không làm mới
        }
    }
}