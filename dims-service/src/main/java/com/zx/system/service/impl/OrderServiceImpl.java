package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Order;
import com.zx.system.mapper.DrugMapper;
import com.zx.system.mapper.OrderMapper;
import com.zx.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author zhangxi
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DrugMapper drugMapper;


    @Override
    public Boolean create(Order order) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        order.setCreateBy(loginUser.getUsername());
        orderMapper.create(order);
        return true;
    }
}
