package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal tax() {
        return price
                .divide(valueOf(100))
                .multiply(category.getTaxPercentage())
                .setScale(2, HALF_UP);
    }

    public BigDecimal priceWithTax() {
        return price
                .add(tax())
                .setScale(2, HALF_UP);
    }
}
