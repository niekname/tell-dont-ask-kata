package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(CreateOrderRequest request) {
        Order order = Order.create();

        request.getRequests().forEach(
                itemRequest -> {
                    Product product = productCatalog.getByName(itemRequest.getProductName());
                    order.orderItem(product, itemRequest.getQuantity());
                }
        );

        orderRepository.save(order);
    }
}
