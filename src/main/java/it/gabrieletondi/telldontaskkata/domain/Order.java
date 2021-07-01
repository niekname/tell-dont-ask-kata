package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.ShippedOrdersCannotBeChangedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Order {
    private BigDecimal total = new BigDecimal("0.00");
    private String currency = "EUR";
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal tax = new BigDecimal("0.00");
    private OrderStatus status = OrderStatus.CREATED;
    private int id;

    public static Order create() {
        return new Order();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void approve() {
        if (status.equals(OrderStatus.SHIPPED)) throw new ShippedOrdersCannotBeChangedException();
        if (status.equals(OrderStatus.REJECTED)) throw new RejectedOrderCannotBeApprovedException();
        status = OrderStatus.APPROVED;
    }

    public void reject() {
        if (status.equals(OrderStatus.SHIPPED)) throw new ShippedOrdersCannotBeChangedException();
        if (status.equals(OrderStatus.APPROVED)) throw new ApprovedOrderCannotBeRejectedException();
        status = OrderStatus.REJECTED;
    }

    public void orderItem(Product product, int quantity) {
        final OrderItem orderItem = OrderItem.create(product, quantity);
        items.add(orderItem);

        total = total.add(orderItem.priceWithTax());
        tax = tax.add(orderItem.getTax());
    }
}
