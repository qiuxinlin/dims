package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Drug;
import com.zx.system.domain.Order;
import com.zx.system.domain.vo.OrderVo;
import com.zx.system.mapper.DrugMapper;
import com.zx.system.mapper.OrderMapper;
import com.zx.system.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OrderVo> findByParam(Order order) {
        List<Order> orders = orderMapper.findByParam(order);
        List<OrderVo> orderVos = new ArrayList<>();
        for (Order temp : orders) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(temp, orderVo);
            Drug drug = drugMapper.findById(temp.getDrugId());
            orderVo.setDrugName(drug.getName());
            orderVos.add(orderVo);
        }
        return orderVos;
    }
}
