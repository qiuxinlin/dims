package com.zx.system.service;

import com.zx.system.domain.StockRecord;

/**
 * IStockService
 *
 * @author zhangxi
 */
public interface IStockService {

    Boolean stockIn(StockRecord stockRecord);

    Boolean stockOut(StockRecord stockRecord);
}
