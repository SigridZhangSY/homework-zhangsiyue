package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.product.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ProductMapper {
    Product findById(long id);

    void save(@Param("info") Map<String, Object> info, @Param("user_id") long userId);

    void savePrice(@Param("product_id")long productId, @Param("info") Map<String, Object> info);

    void setPrice(@Param("product_id")long product_id, @Param("price_id")long price_id);
}
