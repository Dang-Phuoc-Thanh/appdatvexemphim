public class Voucher {
    private String title;
    private Long discountAmount;
    private String description;
    private String imageUrl;


    // Constructor
    public Voucher(String title, Long discountAmount , String description, String imageUrl) {
        this.title = title;
        this.discountAmount= discountAmount;
        this.description = description;
        this.imageUrl = imageUrl;

    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }
}

