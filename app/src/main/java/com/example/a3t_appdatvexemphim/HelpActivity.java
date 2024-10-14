package com.example.a3t_appdatvexemphim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;


public class HelpActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemViewLuachonhelpAdapter itemViewLuachonhelpAdapter;
    private List<ItemLucchonhelp> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo dữ liệu cho các lựa chọn
        itemList = new ArrayList<>();
        itemList.add(new ItemLucchonhelp("Vì sao tôi đã đặt vé thành công mà chưa nhận được xác nhận đặt vé?", "Trong vòng 30 phút kể từ khi thanh toán thành công, chúng tôi sẽ gửi bạn mã xác nhận thông tin vé qua email của bạn. Bạn vui lòng kiểm tra cả hòm thư rác. Nếu bạn cần hỗ trợ hay thắc mắc, khiếu nại về xác nhận mã vé thì vui lòng nhắn tin tới Facebook Fanpage 3T Cinemas tại: m.me/ 3Tcinemas trong vòng 60 phút kể từ khi thanh toán vé thành công. Sau khoảng thời gian trên, chúng tôi không chấp nhận giải quyết bất kỳ khiếu nại nào.\n" +
                "\n" +
                "Chúng tôi không chịu trách nhiệm trong trường hợp thông tin địa chỉ email, số điện thoại bạn nhập không chính xác dẫn đến không nhận được thư xác nhận của chúng tôi. Vui lòng kiểm tra kỹ các thông tin này trước khi thực hiện thanh toán. Chúng tôi không hỗ trợ xử lý và không chịu trách nhiệm trong trường hợp đã gửi thư xác nhận mã vé đến địa chỉ email của bạn nhưng vì một lý do nào đó mà bạn không thể đến xem phim (noshow)."));
        itemList.add(new ItemLucchonhelp("Có thể Hủy hoặc thay đổi vé đã mua online được không ?", "Vé đã mua rồi không thể hủy/đổi/trả/hoàn tiền vì bất lý do nào. Chúng tôi chỉ thực hiện hoàn tiền trong trường hợp thẻ của bạn đã bị trừ tiền nhưng hệ thống của chúng tôi công không ghi nhận việc đặt vé của bạn, và bạn không nhận được xác nhận đặt vé thành công"));
        itemList.add(new ItemLucchonhelp("Vấn đề chụp hình ,ghi âm tại rạp?", "Việc quay phim, chụp hình trong phòng chiếu là vi phạm Luật sở hữu trí tuệ của nước CNXH CN Việt Nam, theo khung xử phạt hành chính lên đến 35.000.000 VND"));
itemList.add(new ItemLucchonhelp("Tôi có được mang đồ ăn từ bên ngoài vào không ?","Nhằm đảm bảo chất lượng phục vụ bao gồm vệ sinh an toàn thực phẩm và tránh gây nhầm lẫn về đồ ăn bên ngoài và được bán ở rạp, quý khách vui lòng gửi đồ ăn tại quầy Con hoặc tiêu dùng hết trước khi vào bộ phận soát vé. 3T rất cảm ơn sự hợp tác của quý khách"));
        // Thiết lập adapter
        itemViewLuachonhelpAdapter = new ItemViewLuachonhelpAdapter(itemList);
        recyclerView.setAdapter(itemViewLuachonhelpAdapter);


        // Thiết lập sự kiện click cho biểu tượng điện thoại
        ImageView phoneIcon = findViewById(R.id.imageView6);
        phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0877022760"));

                // Kiểm tra quyền gọi điện trước khi thực hiện cuộc gọi
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    // Yêu cầu quyền nếu chưa được cấp
                    Toast.makeText(HelpActivity.this, "Vui lòng cấp quyền gọi điện", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                }
            }
        });
    }
    // Phương thức để xử lý kết quả khi người dùng cấp hoặc từ chối quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Gọi phương thức cha

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Nếu quyền được cấp, thực hiện cuộc gọi
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0877022760"));
                if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                }
            } else {
                // Thông báo nếu người dùng từ chối cấp quyền
                Toast.makeText(this, "Quyền gọi điện bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
