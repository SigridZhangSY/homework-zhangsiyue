package com.thoughtworks.ketsu.infrastructure.repositories;

import com.google.inject.Inject;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.order.Orders;
import com.thoughtworks.ketsu.domain.product.Product;
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
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("items");
        for(Map<String, Object>item : items){
            long product_id = Long.valueOf(item.get("product").toString());
            Optional<Product> product = owner.findProductById(product_id);
            double price_id = product.get().getPrice().getId();
            item.put("price_id", price_id);
        }
        info.replace("items", items);
        info.put("user_id", owner.getId());
        orderMapper.save(info);
        return orderMapper.findById(Long.valueOf(info.get("id").toString()));
    }

    @Override
    public List<Order> getOrdersForUser(User owner) {
        return orderMapper.getOrdersForUser(owner.getId());
    }
}
