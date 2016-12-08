package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.order.Orders;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class OrderRepositoryTest {
    @Inject
    Orders orderRepository;

    @Inject
    Users userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = userRepository.createUser(TestHelper.userMap("aaa@www.com", "pass"));

    }

    @Test
    public void should_find_order_by_id() throws Exception {
        Optional<Order> order = orderRepository.findById(1);

        assertThat(order.isPresent(), is(true));
        assertThat(order.get().getId(), notNullValue());
        assertThat(order.get().getOwner(), notNullValue());
        assertThat(order.get().getTotalPrice(), is(5.0));
    }

    @Test
    public void should_create_and_find_order() throws Exception {
        Order order = orderRepository.createOrder(TestHelper.orderMap(), user);

        assertThat(order.getId(), notNullValue());
        assertThat(order.getOwner(), notNullValue());
        assertThat(order.getTotalPrice(), is(5.0));
        assertThat(order.getOrderItems().size(), is(1));
    }

    @Test
    public void should_get_orders_for_user() throws Exception {
        orderRepository.createOrder(TestHelper.orderMap(), user);
        List<Order> orders = orderRepository.getOrdersForUser(user);

        assertThat(orders.size(), is(1));
    }
}
