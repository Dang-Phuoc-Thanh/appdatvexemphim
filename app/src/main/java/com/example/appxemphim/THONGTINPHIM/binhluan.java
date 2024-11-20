package com.example.appxemphim.THONGTINPHIM;

public class binhluan
{private String name;
private String comment;
private String rating;

public binhluan(String name, String comment, String rating) {
    this.name = name;
    this.comment = comment;
    this.rating = rating;
}

public String getName() {
    return name;
}

public String getComment() {
    return comment;
}

public String getRating() {
    return rating;
}
}
