package com.example.a3t_appdatvexemphim;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatGheFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatGheFragment extends Fragment {
    private ImageView back;
    private Button datghe;
    private Button gheA01;
    private  Button gheA02;
    private  Button gheA03;
    private  Button gheA04;
    private Button gheB01;
    private  Button gheB02;
    private  Button gheB03;
    private  Button gheB04;
    private Button gheC01;
    private  Button gheC02;
    private  Button gheC03;
    private  Button gheC04;
    private Button gheD01;
    private  Button gheD02;
    private  Button gheD03;
    private  Button gheD04;
    private Button gheE01;
    private  Button gheE02;
    private  Button gheE03;
    private  Button gheE04;
    private boolean isHighlighted = false;

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatGheFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatGheFragment.
     */
    public static DatGheFragment newInstance(String param1, String param2) {
        DatGheFragment fragment = new DatGheFragment();
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
        View view = inflater.inflate(R.layout.fragment_dat_ghe, container, false);
        back = view.findViewById(R.id.imageView2);
        datghe = view.findViewById(R.id.button_datghe);
        gheA01 = view.findViewById(R.id.idghe_A01);
        gheA02 = view.findViewById(R.id.idghe_A02);
        gheA03 = view.findViewById(R.id.idghe_A03);
        gheA04 = view.findViewById(R.id.idghe_A04);
        gheB01 = view.findViewById(R.id.idghe_B01);
        gheB02 = view.findViewById(R.id.idghe_B02);
        gheB03 = view.findViewById(R.id.idghe_B03);
        gheB04 = view.findViewById(R.id.idghe_B04);
        gheC01 = view.findViewById(R.id.idghe_C01);
        gheC02 = view.findViewById(R.id.idghe_C02);
        gheC03 = view.findViewById(R.id.idghe_C03);
        gheC04 = view.findViewById(R.id.idghe_C04);
        gheD01 = view.findViewById(R.id.idghe_D01);
        gheD02 = view.findViewById(R.id.idghe_D02);
        gheD03 = view.findViewById(R.id.idghe_D03);
        gheD04 = view.findViewById(R.id.idghe_D04);

        // Set onClickListener for the button
        datghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new BapNuocFragment()); // Ensure this ID matches the FrameLayout ID in your activity
                transaction.addToBackStack(null);  // Add to backstack to allow navigation back
                transaction.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new SuatChieu();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, newFragment); // Ensure R.id.frame_layout matches the ID of your container layout
                fragmentTransaction.addToBackStack(null); // Optional: add to back stack
                fragmentTransaction.commit();
            }
        });

        // Set onClickListener for the seat button
        gheA01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheA01.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheA01.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheA02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheA02.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheA02.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheA03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheA03.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheA03.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheA04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheA04.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheA04.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB01.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB01.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB02.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB02.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB03.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB03.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB04.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB04.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });
        gheB01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB01.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB01.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB02.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB02.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB03.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB03.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheB04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheB04.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheB04.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheC01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheC01.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheC01.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheC02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheC02.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheC02.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheC03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheC03.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheC03.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheC04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheC04.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheC04.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });
        gheD01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheD01.setBackgroundResource(R.drawable.button_square); // Trở lại màu ban đầu
                } else {
                    gheD01.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheD02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheD02.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheD02.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheD03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheD03.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheD03.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });

        gheD04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHighlighted) {
                    gheD04.setBackgroundResource(R.drawable.button_seat); // Trở lại màu ban đầu
                } else {
                    gheD04.setBackgroundResource(R.drawable.button_highlight); // Đổi sang màu sáng
                }
                isHighlighted = !isHighlighted;
            }
        });





        return view;
    }
}