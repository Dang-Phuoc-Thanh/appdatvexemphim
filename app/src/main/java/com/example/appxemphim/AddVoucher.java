package com.example.appxemphim;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddVoucher extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button button;
    private String mParam1;
    private String mParam2;
    private ImageView back;
    private EditText input;

    public AddVoucher() {
        // Required empty public constructor
    }

    public static AddVoucher newInstance(String param1, String param2) {
        AddVoucher fragment = new AddVoucher();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_voucher, container, false);

        // Initialize views
        back = view.findViewById(R.id.back);
        button = view.findViewById(R.id.button_apdung);
        input = view.findViewById(R.id.input_voucher);

        // Back button click listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new VoucherFragment();

                // Replace the current fragment with the new fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        // Button click listener for applying voucher
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert input to String for proper comparison
                if (input.getText().toString().equals("1234")) {
                    // Show success message
                    Toast.makeText(getContext(), "Đã thêm voucher", Toast.LENGTH_SHORT).show();
                    input.setText("");
                } else {
                    // Show error message or a different message for invalid input
                    Toast.makeText(getContext(), "Mã không hợp lệ", Toast.LENGTH_SHORT).show();
                    input.setText("");
                }
            }
        });

        return view;  // Ensure this is at the end of the method
    }
}
