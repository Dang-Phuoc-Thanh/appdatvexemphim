// TrangChuFragment.java
package com.example.a3t_appdatvexemphim.Trangchu;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a3t_appdatvexemphim.DSphim.DSphimhhFragment;
import com.example.a3t_appdatvexemphim.R;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    private Button but_datve;
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList; // Lưu danh sách phim ban đầu
    private EditText edtSearch;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        initViews(view);
        initViewPager();
        initRecyclerView();
        initSearchEditText();
        but_datve=view.findViewById(R.id.btn_datve);
        but_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new DSphimhhFragment()); // Đảm bảo ID này là ID của FrameLayout trong Home activity
                transaction.addToBackStack(null);  // Thêm vào backstack để quay lại TrangChuFragment nếu cần
                transaction.commit();
            }
        });
        return view;
    }

    private void initViews(View view) {
        rcvCategory = view.findViewById(R.id.rcv_category);
        edtSearch = view.findViewById(R.id.edtSearch);
        viewPager2 = view.findViewById(R.id.viewPagerImagerSlider);
    }

    private void initViewPager() {
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phim6));
        sliderItems.add(new SliderItem(R.drawable.img_phim2));
        sliderItems.add(new SliderItem(R.drawable.img_phim3));
        sliderItems.add(new SliderItem(R.drawable.img_phim4));
        sliderItems.add(new SliderItem(R.drawable.img_phim5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });
    }

    private void initRecyclerView() {
        categoryAdapter = new CategoryAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        // Lấy dữ liệu danh mục ban đầu
        categoryList = getListCategory();
        categoryAdapter.setData(categoryList);
        rcvCategory.setAdapter(categoryAdapter);

        // Thêm sự kiện click cho các mục trong RecyclerView
        categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                if (category.getNameCategory().equals("Phim hoạt hình")) {
                    // Chuyển sang DSphimhhFragment
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, new DSphimhhFragment()); // Đảm bảo ID này là ID của FrameLayout trong Home activity
                    transaction.addToBackStack(null);  // Thêm vào backstack để quay lại TrangChuFragment nếu cần
                    transaction.commit();
                }
            }
        });
    }

    private void initSearchEditText() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần xử lý
            }
        });
    }

    private List<Category> getListCategory() {
        List<Category> listCategory = new ArrayList<>();

        List<com.example.a3t_appdatvexemphim.Trangchu.FILM> listFilmhh = new ArrayList<>();
        listFilmhh.add(new FILM("Xác ướp - Cuộc phiêu lưu đến LonDon", R.drawable.phh11));
        listFilmhh.add(new FILM("Mèo béo siêu đẳng", R.drawable.phh2));
        listFilmhh.add(new FILM("SPY x FAMILY", R.drawable.phh3));
        listFilmhh.add(new FILM("Bản giao hưỏng địa cầu", R.drawable.phh5));
        listFilmhh.add(new FILM("Truyền thuyết nhẫn thuật ninja", R.drawable.phh6));

        List<FILM> listFilmhd = new ArrayList<>();
        listFilmhd.add(new FILM("Đẹp trai thấy sai sai", R.drawable.img_phim2));
        listFilmhd.add(new FILM("Đố anh còng được tôi", R.drawable.phd2));
        listFilmhd.add(new FILM("Mạng đổi mạng", R.drawable.phd3));
        listFilmhd.add(new FILM("Đặc vụ xuyên quốc gia", R.drawable.phd4));
        listFilmhd.add(new FILM("Trừng phạt", R.drawable.phd5));

        List<FILM> listFilmkd = new ArrayList<>();
        listFilmkd.add(new FILM("Minh hôn1", R.drawable.pkinhdi1));
        listFilmkd.add(new FILM("Kết nối tử thần", R.drawable.pkinhdi2));
        listFilmkd.add(new FILM("Tìm kiếm tài năng âm phủ", R.drawable.pkinhdi3));
        listFilmkd.add(new FILM("Quỷ án", R.drawable.pkinhdi4));
        listFilmkd.add(new FILM("Không nói điều dữ", R.drawable.pkinhdi5));

        List<FILM> listFilmtc = new ArrayList<>();
        listFilmtc.add(new FILM("Ngược dòng thời gian để yêu anh", R.drawable.ptc1));
        listFilmtc.add(new FILM("Cua lại vợ bầu", R.drawable.ptc2));
        listFilmtc.add(new FILM("Hai muối", R.drawable.ptc3));
        listFilmtc.add(new FILM("Mắt biếc", R.drawable.ptc4));
        listFilmtc.add(new FILM("Trước giờ “YÊU”", R.drawable.ptc5));

        listCategory.add(new Category("Phim hoạt hình", listFilmhh));
        listCategory.add(new Category("Phim hành động", listFilmhd));
        listCategory.add(new Category("Phim Kinh dị", listFilmkd));
        listCategory.add(new Category("Phim Tình cảm", listFilmtc));
        listCategory.add(new Category("Phim Hài", listFilmhh));

        return listCategory;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    private void filter(String text) {
        List<Category> filteredList = new ArrayList<>();
        for (Category item : categoryList) {
            if (item.getNameCategory().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        categoryAdapter.setData(filteredList);
    }
}