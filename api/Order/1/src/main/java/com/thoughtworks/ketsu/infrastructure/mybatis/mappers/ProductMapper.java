package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.product.Product;

public interface ProductMapper {
    Product findById(long id);
}
