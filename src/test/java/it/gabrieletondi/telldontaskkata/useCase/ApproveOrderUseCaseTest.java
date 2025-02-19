package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ApproveOrderUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final ApproveOrderUseCase useCase = new ApproveOrderUseCase(orderRepository);

    @Test
    public void approvedExistingOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.CREATED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        ApproveOrderRequest request = new ApproveOrderRequest();
        request.setOrderId(1);

        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertThat(savedOrder.getStatus(), is(OrderStatus.APPROVED));
    }

    @Test(expected = RejectedOrderCannotBeApprovedException.class)
    public void cannotApproveRejectedOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.REJECTED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        ApproveOrderRequest request = new ApproveOrderRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = ShippedOrdersCannotBeChangedException.class)
    public void shippedOrdersCannotBeApproved() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.SHIPPED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        ApproveOrderRequest request = new ApproveOrderRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }
}
