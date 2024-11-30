package com.example.a3t_appdatvexemphim.THONGTINPHIM;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.Video.Video_Fragment;

import java.util.ArrayList;
import java.util.List;

public class TTPhimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TTPhimFragment() {
        // Required empty public constructor
    }

    public static TTPhimFragment newInstance(String param1, String param2) {
        TTPhimFragment fragment = new TTPhimFragment();
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
        View view = inflater.inflate(R.layout.fragment_ttphim, container, false);


        // Set OnClickListener for Xemvideo LinearLayout
        LinearLayout xemVideoLayout = view.findViewById(R.id.Xemvideo);
        xemVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new Video_Fragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Thêm log iđể kiểm tra ListView
        ListView listView = view.findViewById(R.id.listview_chitietphim);
        Log.d("TTPhimFragment", "ListView: " + listView);

        // Thêm phần đánh giá vào danh sách
        LinearLayout noidungphimLayout = view.findViewById(R.id.noidungphim);

        // Tạo dữ liệu mẫu cho danh sách đánh giá
        List<binhluan> reviews = new ArrayList<>();
        reviews.add(new binhluan("Lê Thị Huyền Trang", "Phim xây dựng hình ảnh đẹp, nhưng nội dung chưa lôi cuốn, các tình huống dễ đoán. Toàn cảnh máu me", "4.2/10 - Tạm"));
        reviews.add(new binhluan("Lê Minh Toàn", "Nói chung thì cốt truyện cũng khá oke, diễn xuất với lời thoại bắt trend cực nhanh, cười ná thở , nên xem", "8/10 - Ổn"));
        reviews.add(new binhluan("Đặng Phước Thạnh", "Tuyệt cà là vời , nên xem . đặc biệt nên đi cùng ny", "8,5/10 - Ổn"));

        // Tạo và gán adapter cho ListView
        RatingAdapter adapter = new RatingAdapter(getActivity(), reviews);
        listView.setAdapter(adapter);

        return view;
    }
}
