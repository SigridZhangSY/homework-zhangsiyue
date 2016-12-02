package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class UserTest {
    @Inject
    Users userRepository;

    @Test
    public void should_find_product_by_id() throws Exception {
        User user = userRepository.findById(1).get();
        Optional<Product> product = user.findProductById(1);

        assertThat(product.isPresent(), is(true));
        assertThat(product.get().getId(), notNullValue());
        assertThat(product.get().getName(), notNullValue());
        assertThat(product.get().getPrice(), notNullValue());
        assertThat(product.get().getOwner(), notNullValue());
    }
}
