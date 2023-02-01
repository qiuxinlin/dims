package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Drug;
import com.zx.system.domain.Order;
import com.zx.system.domain.Stock;
import com.zx.system.domain.StockRecord;
import com.zx.system.domain.vo.OrderVo;
import com.zx.system.mapper.DrugMapper;
import com.zx.system.mapper.OrderMapper;
import com.zx.system.mapper.StockMapper;
import com.zx.system.mapper.StockRecordMapper;
import com.zx.system.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public Boolean create(Order order) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        order.setStatus(1);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean sendOut(List<Integer> orderIds) {
        Integer sendOutStatus = 2;
        List<Order> orders = orderMapper.findByIds(orderIds);
        Map<Integer, Order> orderMap = orders.stream().collect(Collectors.toMap(Order::getDrugId, Order -> Order));
        List<Integer> drugIds = orders.stream().map(Order::getDrugId).collect(Collectors.toList());
        List<Stock> stocks = stockMapper.findByDrugIds(drugIds);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        for (Stock stock : stocks) {
            Order order = orderMap.get(stock.getDrugId());
            if (order.getStatus().equals(sendOutStatus)) {
                throw new IllegalArgumentException("药单:" + order.getId() + "已发药");
            }
            if (order.getQuantity().compareTo(stock.getQuantity()) > 0) {
                throw new IllegalArgumentException("药品:" + order.getDrugId() + "库存不足");
            }
            Integer qty = stock.getQuantity() - order.getQuantity();
            Stock updateStock = new Stock();
            BeanUtils.copyProperties(stock, updateStock);
            updateStock.setQuantity(qty);
            stockMapper.update(updateStock);
            //insert stock record
            StockRecord stockRecord = new StockRecord();
            stockRecord.setStockId(stock.getId());
            stockRecord.setDrugId(stock.getDrugId());
            stockRecord.setOperationType(3);
            stockRecord.setQuantity(order.getQuantity());
            stockRecord.setCreateBy(loginUser.getUsername());
            stockRecordMapper.create(stockRecord);
            //update order status
            Order updateStatus = new Order();
            updateStatus.setId(order.getId());
            updateStatus.setStatus(sendOutStatus);
            updateStatus.setUpdateBy(loginUser.getUsername());
            orderMapper.update(updateStatus);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean sendBack(List<Integer> orderIds) {
        Integer sendBackStatus = 3;
        List<Order> orders = orderMapper.findByIds(orderIds);
        Map<Integer, Order> orderMap = orders.stream().collect(Collectors.toMap(Order::getDrugId, Order -> Order));
        List<Integer> drugIds = orders.stream().map(Order::getDrugId).collect(Collectors.toList());
        List<Stock> stocks = stockMapper.findByDrugIds(drugIds);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        for (Stock stock : stocks) {
            Order order = orderMap.get(stock.getDrugId());
            if (order.getStatus().equals(sendBackStatus)) {
                throw new IllegalArgumentException("药单:" + order.getId() + "已退药");
            }
            Integer qty = stock.getQuantity() + order.getQuantity();
            Stock updateStock = new Stock();
            BeanUtils.copyProperties(stock, updateStock);
            updateStock.setQuantity(qty);
            stockMapper.update(updateStock);
            //insert stock record
            StockRecord stockRecord = new StockRecord();
            stockRecord.setStockId(stock.getId());
            stockRecord.setDrugId(stock.getDrugId());
            stockRecord.setOperationType(4);
            stockRecord.setQuantity(order.getQuantity());
            stockRecord.setCreateBy(loginUser.getUsername());
            stockRecordMapper.create(stockRecord);
            //update order status
            Order updateStatus = new Order();
            updateStatus.setId(order.getId());
            updateStatus.setStatus(sendBackStatus);
            updateStatus.setUpdateBy(loginUser.getUsername());
            orderMapper.update(updateStatus);
        }
        return true;
    }
}
