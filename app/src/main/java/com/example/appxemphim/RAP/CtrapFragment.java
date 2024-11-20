package com.example.appxemphim.RAP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.appxemphim.DSphim.dsFILMHH;
import com.example.appxemphim.DSphim.listAdapter;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.ActivityCtrapBinding;

import java.util.ArrayList;

public class CtrapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ActivityCtrapBinding binding;
    private Rap rap;

    public CtrapFragment() {
        // Required empty public constructor
    }
    public static CtrapFragment newInstance(Rap rap) {
        CtrapFragment fragment = new CtrapFragment();
        Bundle args = new Bundle();
        args.putSerializable("rap", rap); // Giả sử Rap implements Serializable
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
        binding = ActivityCtrapBinding.inflate(inflater, container, false);

        // Nhận dữ liệu từ Bundle
        dsFILMHH selectedFilm = (dsFILMHH) getArguments().getSerializable("selectedFilm");



        // Dữ liệu cho danh sách phim
        int[] imageId = {
                R.drawable.phim6,
                R.drawable.phh11,
                R.drawable.phh2,
                R.drawable.phh3,
                R.drawable.phh5,
                R.drawable.phh6,
                R.drawable.phh11
        };
        String[] name = {
                "SPY x FAMILY",
                "Xác ướp - Cuộc phiêu lưu đến LonDon",
                "Mèo béo siêu đẳng",
                "SPY x FAMILY",
                "Bản giao hưởng địa cầu",
                "Truyền thuyết nhẫn thuật ninja",
                "Sonic the Hedgehog 2 (2024)"
        };
        String[] time = {
                "Thời lượng: 180 Phút",
                "Thời lượng: 90 Phút",
                "Thời lượng: 150 Phút",
                "Thời lượng: 200 Phút",
                "Thời lượng: 90 Phút",
                "Thời lượng: 180 Phút",
                "Thời lượng: 150 Phút"
        };
        String[] day = {
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024",
                "Khởi chiếu: 20/09/2024"
        };
        String[] datve = {
                "Đặt vé",
                "Đặt vé",
                "Đặt vé",
                "Đặt vé",
                "Đặt vé",
                "Đặt vé",
                "Đặt vé"
        };

        // Tạo danh sách đối tượng dsFILMHH
        ArrayList<dsFILMHH> dsFILMHHArrayList = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            dsFILMHH dsfilmhh = new dsFILMHH(name[i], time[i], day[i], datve[i], imageId[i]);
            dsFILMHHArrayList.add(dsfilmhh);
        }

        // Khởi tạo adapter
        listAdapter ListAdapter = new listAdapter(getContext(), dsFILMHHArrayList);

        // Gán adapter cho ListView
        binding.listview.setAdapter(ListAdapter);

        // Bắt sự kiện click cho ListView
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Khi người dùng click vào một mục trong danh sách, chuyển đến CTRapFragment
                CtrapFragment ctrapFragment = new CtrapFragment();

                // Truyền dữ liệu nếu cần thiết qua Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", dsFILMHHArrayList.get(position));
                ctrapFragment.setArguments(bundle);

                // Thay thế Fragment hiện tại bằng CTRapFragment
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameRAP, ctrapFragment) // R.id.frameRAP là ID của View chứa Fragment
                        .addToBackStack(null) // Thêm vào back stack để có thể quay lại
                        .commit();
            }

        });

        // Thiết lập sự kiện nhấp chuột cho nút quay lại
        binding.icQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến CinemaFragment
                CinemaFragment cinemaFragment = new CinemaFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameRAP, cinemaFragment) // R.id.frameRAP là ID của View chứa Fragment
                        .addToBackStack(null) // Thêm vào stack để có thể quay lại
                        .commit();
            }
        });

        // Thiết lập padding cho button nếu cần
        ViewCompat.setOnApplyWindowInsetsListener(binding.btnKHOIPHUC, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return binding.getRoot();
    }
}
