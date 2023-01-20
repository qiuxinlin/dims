package com.zx.system.mapper;

import com.zx.system.domain.Order;

import java.util.List;

/**
 * OrderMapper
 *
 * @author zhangxi
 */
public interface OrderMapper {

    int create(Order order);

    List<Order> findByParam(Order order);
}
