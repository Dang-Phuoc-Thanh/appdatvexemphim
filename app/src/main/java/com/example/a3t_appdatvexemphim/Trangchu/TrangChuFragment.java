// TrangChuFragment.java
package com.example.a3t_appdatvexemphim.Trangchu;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrangChuFragment extends Fragment {
    private Button but_datve;
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList = new ArrayList<>(); // Lưu danh sách phim ban đầu
    private EditText edtSearch;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;
    private List<FILM> listFilmhh = new ArrayList<>();
    private List<FILM> listFilmhd = new ArrayList<>();
    private List<FILM> listFilmtc = new ArrayList<>();
    private List<FILM> listFilmkd = new ArrayList<>();
    private List<ClassPhim> DSFilmhh = new ArrayList<>();
    private List<ClassPhim> DSFilmhd = new ArrayList<>();
    private List<ClassPhim> DSFilmtc = new ArrayList<>();
    private List<ClassPhim> DSFilmkd = new ArrayList<>();
    private List<ClassPhim> dsPhim = new ArrayList<>();
    private Map<Long, Long> phimTheLoaiMap = new HashMap<>();
    private DatabaseReference mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        mData = FirebaseDatabase.getInstance().getReference();

        initViews(view);
        loadPhimTheLoaiData();
        initViewPager();
        initRecyclerView();
        initSearchEditText();
        but_datve = view.findViewById(R.id.btn_datve);
        but_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new DSphimhhFragment()); // Đảm bảo ID này là ID của FrameLayout trong Home activity
                transaction.addToBackStack(null);  // Thêm vào backstack để quay lại TrangChuFragment nếu cần
                transaction.commit();
            }
        });

        // Khởi tạo sliderRunnable
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                int totalItems = viewPager2.getAdapter().getItemCount();
                viewPager2.setCurrentItem((currentItem + 1) % totalItems);
            }
        };

        return view;
    }

    private void loadPhimTheLoaiData() {
        mData.child("PHIM_THELOAIPHIM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phimTheLoaiMap.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Long maPhim = snapshot.child("MaPhim").getValue(Long.class);
                        Long maTheLoaiPhim = snapshot.child("MaTheLoaiPhim").getValue(Long.class);
                        if (maPhim != null && maTheLoaiPhim != null) {
                            phimTheLoaiMap.put(maPhim, maTheLoaiPhim);
                        }
                    } catch (Exception e) {
                        Log.e("PhimTheLoai", "Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
                    }
                }
                loadPhimData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TrangChuFragment", "loadPhimTheLoaiData:onCancelled", databaseError.toException());
                Toast.makeText(getContext(), "Lỗi khi tải dữ liệu thể loại phim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPhimData() {
        mData.child("PHIM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dsPhim.clear();
                listFilmhh.clear();
                listFilmhd.clear();
                listFilmkd.clear();
                listFilmtc.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Long maPhim = snapshot.child("MaPhim").getValue(Long.class);
                        Long maTheLoaiPhim = phimTheLoaiMap.get(maPhim);

                        if (maTheLoaiPhim != null) {
                            ClassPhim phim = snapshot.getValue(ClassPhim.class);
                            if (phim != null) {
                                dsPhim.add(phim);
                                FILM film = new FILM(phim.TenPhim, phim.HinhAnh); // Sử dụng URL hình ảnh từ Firebase
                                switch (maTheLoaiPhim.intValue()) {
                                    case 6: // Hoạt hình
                                        listFilmhh.add(film);
                                        DSFilmhh.add(phim);
                                        break;
                                    case 1: // Hành động
                                        listFilmhd.add(film);
                                        DSFilmhd.add(phim);
                                        break;
                                    case 4: // Kinh dị
                                        listFilmkd.add(film);
                                        DSFilmkd.add(phim);
                                        break;
                                    case 2: // Tình cảm
                                        listFilmtc.add(film);
                                        DSFilmtc.add(phim);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }

                    } catch (Exception e) {
                        Log.e("Phim", "Error parsing data", e);
                    }
                }

                createCategories();
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TrangChuFragment", "loadPhimData:onCancelled", databaseError.toException());
                Toast.makeText(getContext(), "Lỗi khi tải dữ liệu phim", Toast.LENGTH_SHORT).show();
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

    private void filter(String text) {
        List<Category> filteredList = new ArrayList<>();
        for (Category category : categoryList) {
            List<FILM> filteredFilms = new ArrayList<>();
            for (FILM film : category.getFilms()) {
                if (film.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredFilms.add(film);
                }
            }
            if (!filteredFilms.isEmpty()) {
                filteredList.add(new Category(category.getNameCategory(), filteredFilms));
            }
        }
        categoryAdapter.setData(filteredList);
    }


    private void createCategories() {
        categoryList.clear();
        categoryList.add(new Category("Hoạt hình", listFilmhh));
        categoryList.add(new Category("Hành động", listFilmhd));
        categoryList.add(new Category("Kinh dị", listFilmkd));
        categoryList.add(new Category("Tình cảm", listFilmtc));

        // Cập nhật adapter của RecyclerView
        if (categoryAdapter != null) {
            categoryAdapter.notifyDataSetChanged();
        }
    }

    private void updateUI() {
        if (!listFilmhh.isEmpty()) {
            FILM phimDauTien = listFilmhh.get(0);
            String thongTinPhim = "Tên phim: " + phimDauTien.getTitle();
            Toast.makeText(getContext(), thongTinPhim, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Không có phim hoạt hình nào.", Toast.LENGTH_LONG).show();
        }
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
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        rcvCategory.setAdapter(categoryAdapter);

        // Thêm sự kiện click cho các mục trong RecyclerView
        categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                List<ClassPhim> danhsachphim = new ArrayList<>();
                switch (category.getNameCategory()) {
                    case "Hoạt hình":
                        danhsachphim = DSFilmhh;
                        break;
                    case "Hành động":
                        danhsachphim = DSFilmhd;
                        break;
                    case "Kinh dị":
                        danhsachphim = DSFilmkd;
                        break;
                    case "Tình cảm":
                        danhsachphim = DSFilmtc;
                        break;
                }

                Bundle mybundle = new Bundle();
                mybundle.putParcelableArrayList("danhsachphim", new ArrayList<>(danhsachphim)); // Sử dụng putParcelableArrayList để truyền danh sách phim

                DSphimhhFragment fragment = new DSphimhhFragment();
                fragment.setArguments(mybundle);

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, fragment); // Đảm bảo ID này là ID của FrameLayout trong Home activity
                transaction.addToBackStack(null);  // Thêm vào backstack để quay lại TrangChuFragment nếu cần
                transaction.commit();
            }
        });
    }
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


}