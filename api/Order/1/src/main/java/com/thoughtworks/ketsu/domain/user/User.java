package com.thoughtworks.ketsu.domain.user;

import com.google.inject.Inject;
import com.thoughtworks.ketsu.domain.Aggregation;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class User implements Record {
    private long id;
    private String email;

    @Inject
    ProductMapper productMapper;

    private Aggregation<Order> orders;

    public User(){}

    public User(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public Aggregation<Order> getOrders() {
        return orders;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.userUrl(User.this));
            put("id", id);
            put("email", email);
        }};
    }

    public Optional<Product> findProductById(long id){
        return Optional.ofNullable(productMapper.findById(id));
    }

    public Product createProduct(Map<String, Object> info){
        productMapper.save(info, id);
        Long id = Long.valueOf(String.valueOf(info.get("id")));
        Product product = productMapper.findById(id);
        product.changePrice(info);
        return productMapper.findById(id);
    }

    public List<Product> getAllProductsForUser(){
        return productMapper.getProductsForUser(id);
    }

    public void changeProductPrice(Product product, Map<String, Object> info){
        product.changePrice(info);
    }

    public String getEmail() {
        return email;
    }
}
