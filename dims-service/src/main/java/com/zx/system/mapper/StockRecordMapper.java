package com.zx.system.mapper;

import com.zx.system.domain.StockRecord;

import java.util.List;

/**
 * StockRecordMapper
 *
 * @author zhangxi
 */
public interface StockRecordMapper {

    int create(StockRecord stockRecord);

    List<StockRecord> findByParam(StockRecord stockRecord);
}
