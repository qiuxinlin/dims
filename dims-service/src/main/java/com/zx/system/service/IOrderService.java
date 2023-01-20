package com.zx.system.service;

import com.zx.system.domain.Order;
import com.zx.system.domain.vo.OrderVo;

import java.util.List;

/**
 * IOrderService
 *
 * @author zhangxi
 */
public interface IOrderService {

    Boolean create(Order order);

    List<OrderVo> findByParam(Order order);
}
