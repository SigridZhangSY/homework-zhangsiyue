package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.Users;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
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
        assertThat(product.get().getPriceOfTheTime(), notNullValue());
        assertThat(product.get().getOwner(), notNullValue());
    }

    @Test
    public void should_create_product() throws Exception {
        User user = userRepository.findById(1).get();
        Product product = user.createProduct(TestHelper.productMap("test2"));

        assertThat(product.getPriceOfTheTime(), notNullValue());
        assertThat(product.getName(), notNullValue());
        assertThat(product.getOwner(), notNullValue());
    }

    @Test
    public void should_get_products_for_user() throws Exception {
        User user = userRepository.createUser(TestHelper.userMap("bbb@www.com", "pass"));
        Product product = user.createProduct(TestHelper.productMap("test"));
        List<Product> products = user.getAllProductsForUser();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getId(), is(product.getId()));
        assertThat(products.get(0).getName(), is(product.getName()));
        assertThat(products.get(0).getPriceOfTheTime(), is(product.getPriceOfTheTime()));
        assertThat(products.get(0).getOwner().getId(), is(product.getOwner().getId()));
    }
}
