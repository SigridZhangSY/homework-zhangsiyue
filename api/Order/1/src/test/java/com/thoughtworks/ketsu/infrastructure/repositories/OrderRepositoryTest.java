package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.order.Orders;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class OrderRepositoryTest {
    @Inject
    Orders orderRepository;

    @Test
    public void should_find_order_by_id() throws Exception {
        Optional<Order> order = orderRepository.findById(1);

        assertThat(order.isPresent(), is(true));
        assertThat(order.get().getId(), notNullValue());
        assertThat(order.get().getOwner(), notNullValue());
        assertThat(order.get().getTotalPrice(), is(5.0));
    }
}
