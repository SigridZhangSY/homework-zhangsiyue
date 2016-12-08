package com.thoughtworks.ketsu.infrastructure.repositories;

import com.google.inject.Inject;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.order.Orders;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepository implements Orders {
    @Inject
    OrderMapper orderMapper;
    @Override
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }

    @Override
    public Order createOrder(Map<String, Object> info, User owner) {
        return null;
    }

    @Override
    public List<Order> getOrdersForUser(User owner) {
        return null;
    }
}
