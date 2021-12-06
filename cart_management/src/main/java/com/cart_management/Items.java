package com.cart_management;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Items {
    Category Category;
    String item;
    Integer quantity;
    Double totalPrice;
    Double price;

}
