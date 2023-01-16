package com.zx.system.service;

import com.zx.system.domain.StockRecord;
import com.zx.system.domain.vo.StockRecordVo;

import java.util.List;

/**
 * IStockService
 *
 * @author zhangxi
 */
public interface IStockService {

    Boolean stockIn(StockRecord stockRecord);

    Boolean stockOut(StockRecord stockRecord);

    List<StockRecordVo> findRecordsByParam(StockRecord stockRecord);
}
