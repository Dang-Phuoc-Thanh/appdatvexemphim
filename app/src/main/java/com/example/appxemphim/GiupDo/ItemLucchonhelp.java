package com.example.appxemphim.GiupDo;

public class ItemLucchonhelp {
    private String title;
    private String content;
    private boolean expanded;

    public ItemLucchonhelp(String title, String content) {
        this.title = title;
        this.content = content;
        this.expanded = false;  // Mặc định là không mở rộng
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}

