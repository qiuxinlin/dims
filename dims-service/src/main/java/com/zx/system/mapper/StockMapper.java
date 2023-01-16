package com.zx.system.mapper;

import com.zx.system.domain.Stock;

import java.util.List;

/**
 * StockMapper
 *
 * @author zhangxi
 */
public interface StockMapper {

    int create(Stock stock);

    Stock findByDrugId(Integer drugId);

    int update(Stock stock);

    List<Stock> findByDrugIds(List<Integer> drugIds);
}
