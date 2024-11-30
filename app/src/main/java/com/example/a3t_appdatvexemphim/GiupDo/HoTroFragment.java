package com.example.a3t_appdatvexemphim.GiupDo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3t_appdatvexemphim.KhacFragment;
import com.example.a3t_appdatvexemphim.R;

import java.util.ArrayList;
import java.util.List;

public class HoTroFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemViewLuachonhelpAdapter itemViewLuachonhelpAdapter;
    private List<ItemLucchonhelp> itemList;

    public HoTroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ho_tro, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tạo dữ liệu cho các lựa chọn
        itemList = new ArrayList<>();
        itemList.add(new ItemLucchonhelp("Vì sao tôi đã đặt vé thành công mà chưa nhận được xác nhận đặt vé?",
                "Trong vòng 30 phút kể từ khi thanh toán thành công, chúng tôi sẽ gửi bạn mã xác nhận thông tin vé qua email của bạn. ..."));
        itemList.add(new ItemLucchonhelp("Có thể Hủy hoặc thay đổi vé đã mua online được không ?",
                "Vé đã mua rồi không thể hủy/đổi/trả/hoàn tiền vì bất lý do nào. ..."));
        itemList.add(new ItemLucchonhelp("Vấn đề chụp hình, ghi âm tại rạp?",
                "Việc quay phim, chụp hình trong phòng chiếu là vi phạm Luật sở hữu trí tuệ của nước ..."));
        itemList.add(new ItemLucchonhelp("Tôi có được mang đồ ăn từ bên ngoài vào không ?",
                "Nhằm đảm bảo chất lượng phục vụ bao gồm vệ sinh an toàn thực phẩm và tránh gây nhầm lẫn về đồ ăn bên ngoài ..."));

        // Thiết lập adapter
        itemViewLuachonhelpAdapter = new ItemViewLuachonhelpAdapter(itemList);
        recyclerView.setAdapter(itemViewLuachonhelpAdapter);

        // Thiết lập sự kiện click cho biểu tượng điện thoại
        ImageView phoneIcon = view.findViewById(R.id.imageView6);
        phoneIcon.setOnClickListener(v -> makePhoneCall());

        // Thiết lập sự kiện click cho nút quay lại
        ImageView backButton = view.findViewById(R.id.ic_quaylai);
        backButton.setOnClickListener(v -> goBackToKhacFragment());

        return view;
    }

    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0877022760"));

        // Kiểm tra quyền gọi điện
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            // Yêu cầu quyền nếu chưa được cấp
            Toast.makeText(getContext(), "Vui lòng cấp quyền gọi điện", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
        }
    }

    private void goBackToKhacFragment() {
        // Tạo một instance của KhacFragment
        Fragment khacFragment = new KhacFragment();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, khacFragment); // Đảm bảo ID khớp với container
        fragmentTransaction.addToBackStack(null); // Tùy chọn: thêm vào back stack
        fragmentTransaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(getContext(), "Quyền gọi điện bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
