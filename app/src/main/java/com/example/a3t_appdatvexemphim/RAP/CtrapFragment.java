package com.example.a3t_appdatvexemphim.RAP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.a3t_appdatvexemphim.DSphim.dsFILMHH;
import com.example.a3t_appdatvexemphim.DSphim.listAdapter1;
import com.example.a3t_appdatvexemphim.R;
import com.example.a3t_appdatvexemphim.databinding.ActivityCtrapBinding;

import java.util.ArrayList;

public class CtrapFragment extends Fragment {
    private ActivityCtrapBinding binding;
    private Rap selectedRap;

    public CtrapFragment() {
        // Required empty public constructor
    }

    public static CtrapFragment newInstance(Rap rap) {
        CtrapFragment fragment = new CtrapFragment();
        Bundle args = new Bundle();
        args.putSerializable("rap", rap); // Assuming Rap implements Serializable
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the binding object
        binding = ActivityCtrapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Retrieve data from Bundle
        if (getArguments() != null) {
            selectedRap = (Rap) getArguments().getSerializable("rap");
        }

        // Display rap information
        if (selectedRap != null) {
            TextView textView = binding.textView3;
            textView.setText(selectedRap.getTenRap());
        }

        // Retrieve film list from Bundle or create default list
        ArrayList<dsFILMHH> dsFILMHHArrayList;
        if (getArguments() != null && getArguments().getSerializable("filmList") != null) {
            dsFILMHHArrayList = (ArrayList<dsFILMHH>) getArguments().getSerializable("filmList");
        } else {
            dsFILMHHArrayList = new ArrayList<>();
            // Add default data to dsFILMHHArrayList if needed
            String[] imageUrls = {
                    "https://example.com/path/to/image1.jpg",
                    "https://example.com/path/to/image2.jpg",
                    "https://example.com/path/to/image3.jpg",
                    "https://example.com/path/to/image4.jpg",
                    "https://example.com/path/to/image5.jpg",
                    "https://example.com/path/to/image6.jpg",
                    "https://example.com/path/to/image7.jpg"
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

            for (int i = 0; i < imageUrls.length; i++) {
                dsFILMHH dsfilmhh = new dsFILMHH(name[i], time[i], day[i], datve[i], imageUrls[i]);
                dsFILMHHArrayList.add(dsfilmhh);
            }
        }

        // Initialize adapter
        listAdapter1 ListAdapter = new listAdapter1(getContext(), dsFILMHHArrayList);

        // Set adapter to ListView
        binding.listview.setAdapter(ListAdapter);

        // Set click listener for ListView
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When an item is clicked, navigate to CTRapFragment
                CtrapFragment ctrapFragment = new CtrapFragment();

                // Pass data if needed through Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFilm", dsFILMHHArrayList.get(position));
                bundle.putSerializable("filmList", dsFILMHHArrayList); // Save film list
                ctrapFragment.setArguments(bundle);

                // Replace current Fragment with CTRapFragment
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameRAP, ctrapFragment) // R.id.frameRAP is the ID of the View containing the Fragment
                        .addToBackStack(null) // Add to back stack to allow back navigation
                        .commit();
            }
        });

        // Set click listener for back button
        binding.icQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pop the back stack to return to the previous fragment
                getParentFragmentManager().popBackStack();
            }
        });

        // Set padding for button if needed
        ViewCompat.setOnApplyWindowInsetsListener(binding.btnKHOIPHUC, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks
    }
}