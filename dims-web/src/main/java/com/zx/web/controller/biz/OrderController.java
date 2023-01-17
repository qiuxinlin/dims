package com.zx.web.controller.biz;

import com.zx.common.core.controller.BaseController;
import com.zx.common.core.domain.AjaxResult;
import com.zx.system.domain.Order;
import com.zx.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
