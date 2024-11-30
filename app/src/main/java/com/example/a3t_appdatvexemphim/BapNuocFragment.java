package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BapNuocFragment extends Fragment {

    private Button tieptuc;
    private ImageView back, tru1, tru2, cong1, cong2;
    private EditText tongtien;
    private TextView soluong1, soluong2;
    private int quantity1 = 0, quantity2 = 0;

    public BapNuocFragment() { }

    public static BapNuocFragment newInstance(String param1, String param2) {
        BapNuocFragment fragment = new BapNuocFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bap_nuoc2, container, false);

        tieptuc = view.findViewById(R.id.but_tieptuc);
        back = view.findViewById(R.id.but_back);
        soluong1 = view.findViewById(R.id.sl1);
        soluong2 = view.findViewById(R.id.sl2);
        tongtien = view.findViewById(R.id.tongtien);
        tru1 = view.findViewById(R.id.tru1);
        tru2 = view.findViewById(R.id.tru2);
        cong1 = view.findViewById(R.id.cong1);
        cong2 = view.findViewById(R.id.cong2);

        init();

        tieptuc.setOnClickListener(v -> navigateTo(new ThanhToan()));
        back.setOnClickListener(v -> navigateTo(new DatGheFragment()));

        cong1.setOnClickListener(v -> updateQuantity(true, soluong1, true));
        cong2.setOnClickListener(v -> updateQuantity(true, soluong2, false));
        tru1.setOnClickListener(v -> updateQuantity(false, soluong1, true));
        tru2.setOnClickListener(v -> updateQuantity(false, soluong2, false));

        return view;
    }

    private void init() {
        soluong1.setText("0");
        soluong2.setText("0");
        tongtien.setText("0 đ");
    }

    private void updateQuantity(boolean increase, TextView soluong, boolean isFirstItem) {
        int quantity = Integer.parseInt(soluong.getText().toString());
        quantity = increase ? quantity + 1 : (quantity > 0 ? quantity - 1 : 0);
        if (!increase && quantity == 0) {
            Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
        }
        soluong.setText(String.valueOf(quantity));
        if (isFirstItem) quantity1 = quantity;
        else quantity2 = quantity;
        updateTotal();
    }

    private void updateTotal() {
        int total = (quantity1 *99000)+(quantity2*14900); // Replace 10000 with item price if needed
        tongtien.setText(total + " đ");
    }

    private void navigateTo(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
