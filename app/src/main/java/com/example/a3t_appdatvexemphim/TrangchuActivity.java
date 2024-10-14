package com.example.a3t_appdatvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import FILM.FILM;
import category.Category;
import category.CategoryAdapter;

public class TrangchuActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList; // Lưu danh sách phim ban đầu
    private EditText edtSearch;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trangchu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnKHOIPHUC), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViewPager();
        initRecyclerView();
        initSearchEditText();

        // Thêm sự kiện click cho các mục trong RecyclerView
        categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                if (category.getNameCategory().equals("Phim hoạt hình")) {
                    Intent intent = new Intent(TrangchuActivity.this, DSphimhhActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initViewPager() {
        viewPager2 = findViewById(R.id.viewPagerImagerSlider);
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
        rcvCategory = findViewById(R.id.rcv_category);
        categoryAdapter = new CategoryAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        // Lấy dữ liệu danh sách phim ban đầu
        categoryList = getListCategory();
        categoryAdapter.setData(categoryList);
        rcvCategory.setAdapter(categoryAdapter);
    }

    private void initSearchEditText() {
        edtSearch = findViewById(R.id.edtSearch);
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

        List<FILM> listFilmhh = new ArrayList<>();
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
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
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
