package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class ApproveOrderUseCase {
    private final OrderRepository orderRepository;

    public ApproveOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(ApproveOrderRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());
        order.approve();
        orderRepository.save(order);
    }
}
