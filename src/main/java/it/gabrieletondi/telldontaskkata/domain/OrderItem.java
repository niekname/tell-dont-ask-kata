package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;

    public static OrderItem create(Product product, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        return orderItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getTax() {
        return product
                .tax()
                .multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal priceWithTax() {
        return product.priceWithTax()
                .multiply(BigDecimal.valueOf(quantity))
                .setScale(2, HALF_UP);
    }
}
