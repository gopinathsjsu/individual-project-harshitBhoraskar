package cart_management.src.main.java.com.cart_management;

public class ItemInstance {
    String Category;
    String item;
    Integer quantity;
    Double price;
    public String getCategory() {
        return Category;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
