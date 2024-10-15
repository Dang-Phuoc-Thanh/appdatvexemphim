package com.example.a3t_appdatvexemphim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemViewLuachonhelpAdapter extends RecyclerView.Adapter<ItemViewLuachonhelpAdapter.ViewHolder> {
    private List<ItemLucchonhelp> itemList;

    // Constructor của adapter
    public ItemViewLuachonhelpAdapter(List<ItemLucchonhelp> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_luachonhelp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemLucchonhelp item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());

        // Kiểm tra trạng thái mở rộng và cập nhật hiển thị
        boolean isExpanded = item.isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.arrowIcon.setImageResource(isExpanded ? R.drawable.ic_up_arrow : R.drawable.ic_down_arrow);

        // Xử lý sự kiện click vào tiêu đề để mở rộng/thu gọn nội dung
        holder.titleLayout.setOnClickListener(v -> {
            boolean expanded = item.isExpanded();
            item.setExpanded(!expanded);  // Đảo ngược trạng thái mở rộng
            notifyItemChanged(position);  // Cập nhật lại view
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        LinearLayout expandableLayout, titleLayout;
        ImageView arrowIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            titleLayout = itemView.findViewById(R.id.titleLayout); // Layout chứa tiêu đề và icon
            arrowIcon = itemView.findViewById(R.id.arrowIcon);     // Icon mũi tên
        }
    }
}
