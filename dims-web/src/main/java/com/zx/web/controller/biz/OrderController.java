package com.zx.web.controller.biz;

import com.zx.common.core.controller.BaseController;
import com.zx.common.core.domain.AjaxResult;
import com.zx.common.core.page.TableDataInfo;
import com.zx.system.domain.Order;
import com.zx.system.domain.vo.OrderVo;
import com.zx.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * OrderController
 *
 * @author zhangxi
 */
@RestController
@RequestMapping
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/order")
    public AjaxResult create(@RequestBody Order order) {
        Boolean result = orderService.create(order);
        return success(result);
    }

    @PostMapping("/orders")
    public TableDataInfo findByParam(@RequestBody Order order) {
        startPage();
        List<OrderVo> orderVos = orderService.findByParam(order);
        return getDataTable(orderVos);
    }

    @PostMapping("/order/sendOut")
    public AjaxResult sendOut(@RequestBody List<Integer> orderIds) {
        Boolean result = orderService.sendOut(orderIds);
        return success(result);
    }

    @PostMapping("/order/sendBack")
    public AjaxResult sendBack(@RequestBody List<Integer> orderIds) {
        Boolean result = orderService.sendBack(orderIds);
        return success(result);
    }
}
