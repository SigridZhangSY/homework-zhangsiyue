package com.thoughtworks.ketsu.domain.order;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.*;

public class Order implements Record {
    private long id;
    private List<OrderItem> orderItems;
    private User owner;
    private Date time;

    public Order(long id, User owner) {
        this.id = id;
        this.owner = owner;
        orderItems = new ArrayList<>();
    }

    public Order(){}

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.orderUrl(owner, Order.this));
            put("total", getTotalPrice());
        }};
    }

    public long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice(){
        double totalPrice =  orderItems.stream().reduce(
                0.0,
                (sum, orderItem) -> sum = sum + orderItem.getTotalPrice(),
                (u,t) -> u);
        return totalPrice;
    }

    public Optional<Payment> findPayment(){
        return null;
    }

    public Payment createPayment(Map<String, Object>info){
        return null;
    }

    public Optional<RefundRequest> findRefundRequest(long requestId){
        return null;
    }

    public RefundRequest createRefundRequest(Map<String, Object>info, Order order){
        return null;
    }

    public Optional<Refund> findRefund(long refundId){
        return null;
    }

    public Refund createRefund(Map<String, Object>info, Order order){
        return null;
    }

    public List<Refund> getAllRefunds() {
        return null;
    }

    public List<RefundRequest> getAllRefundRequests() {
        return null;
    }
}
