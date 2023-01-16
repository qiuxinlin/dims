package com.zx.web.controller.biz;

import com.zx.common.core.controller.BaseController;
import com.zx.common.core.domain.AjaxResult;
import com.zx.common.core.page.TableDataInfo;
import com.zx.system.domain.StockRecord;
import com.zx.system.domain.vo.StockRecordVo;
import com.zx.system.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * StockController
 *
 * @author zhangxi
 */
@RestController
@RequestMapping
public class StockController extends BaseController {
    @Autowired
    private IStockService stockService;

    @PostMapping("/stockIn")
    public AjaxResult stockIn(@RequestBody StockRecord stockRecord) {
        Boolean result = stockService.stockIn(stockRecord);
        return success(result);
    }

    @PostMapping("/stockOut")
    public AjaxResult stockOut(@RequestBody StockRecord stockRecord) {
        Boolean result = stockService.stockOut(stockRecord);
        return success(result);
    }

    @PostMapping("/stockRecords")
    public TableDataInfo findByParam(@RequestBody StockRecord stockRecord) {
        startPage();
        List<StockRecordVo> stockRecords = stockService.findRecordsByParam(stockRecord);
        return getDataTable(stockRecords);
    }
}
