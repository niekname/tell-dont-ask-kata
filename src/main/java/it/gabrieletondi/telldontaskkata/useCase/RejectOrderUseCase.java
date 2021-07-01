package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class RejectOrderUseCase {
    private final OrderRepository orderRepository;

    public RejectOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(RejectOrderRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());
        order.reject();
        orderRepository.save(order);
    }
}
