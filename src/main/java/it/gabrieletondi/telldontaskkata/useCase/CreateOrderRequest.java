package it.gabrieletondi.telldontaskkata.useCase;

import java.util.List;

public class CreateOrderRequest {
    private List<SellItemRequest> requests;

    public void setRequests(List<SellItemRequest> requests) {
        this.requests = requests;
    }

    public List<SellItemRequest> getRequests() {
        return requests;
    }
}
