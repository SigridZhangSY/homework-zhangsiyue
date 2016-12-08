package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.order.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    Order findById(@Param("id")long id);
}
