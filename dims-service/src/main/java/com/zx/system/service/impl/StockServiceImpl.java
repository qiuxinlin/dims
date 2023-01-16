package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Drug;
import com.zx.system.domain.Stock;
import com.zx.system.domain.StockRecord;
import com.zx.system.domain.vo.StockRecordVo;
import com.zx.system.mapper.DrugMapper;
import com.zx.system.mapper.StockMapper;
import com.zx.system.mapper.StockRecordMapper;
import com.zx.system.service.IStockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * IStockService
 *
 * @author zhangxi
 */
@Service
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Autowired
    private DrugMapper drugMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean stockIn(StockRecord param) {
        if (param.getQuantity() < 0) {
            throw new IllegalArgumentException("数量不能小于0");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Stock stock = stockMapper.findByDrugId(param.getDrugId());
        Integer stockId;
        if (null == stock) {
            //init stock
            Stock initStock = new Stock();
            initStock.setDrugId(param.getDrugId());
            initStock.setQuantity(param.getQuantity());
            initStock.setCreateBy(loginUser.getUsername());
            stockId = stockMapper.create(initStock);
        } else {
            //stock in
            stockId = stock.getId();
            Integer qty = stock.getQuantity();
            qty = qty + param.getQuantity();
            stock.setQuantity(qty);
            stock.setUpdateBy(loginUser.getUsername());
            stockMapper.update(stock);
        }
        //insert stock record
        StockRecord stockRecord = new StockRecord();
        stockRecord.setStockId(stockId);
        stockRecord.setDrugId(param.getDrugId());
        stockRecord.setOperationType(1);
        stockRecord.setQuantity(param.getQuantity());
        stockRecord.setOutbound(param.getOutbound());
        stockRecord.setCreateBy(loginUser.getUsername());
        stockRecordMapper.create(stockRecord);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean stockOut(StockRecord param) {
        if (param.getQuantity() < 0) {
            throw new IllegalArgumentException("数量不能小于0");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Stock stock = stockMapper.findByDrugId(param.getDrugId());
        if (null == stock) {
            throw new IllegalArgumentException("库存信息不存在");
        }
        Integer qty = stock.getQuantity();
        if (param.getQuantity() > qty) {
            throw new IllegalArgumentException("出库数量大于库存，库存不足");
        }
        qty = qty - param.getQuantity();
        stock.setQuantity(qty);
        stock.setUpdateBy(loginUser.getUsername());
        stockMapper.update(stock);
        //insert stock record
        StockRecord stockRecord = new StockRecord();
        stockRecord.setStockId(stock.getId());
        stockRecord.setDrugId(param.getDrugId());
        stockRecord.setOperationType(1);
        stockRecord.setQuantity(param.getQuantity());
        stockRecord.setOutbound(param.getOutbound());
        stockRecord.setCreateBy(loginUser.getUsername());
        stockRecordMapper.create(stockRecord);
        return true;
    }

    @Override
    public List<StockRecordVo> findRecordsByParam(StockRecord stockRecord) {
        List<StockRecord> stockRecords = stockRecordMapper.findByParam(stockRecord);
        List<StockRecordVo> stockRecordVos = new ArrayList<>();
        for (StockRecord temp : stockRecords) {
            StockRecordVo stockRecordVo = new StockRecordVo();
            BeanUtils.copyProperties(temp, stockRecordVo);
            Drug drug = drugMapper.findById(temp.getDrugId());
            stockRecordVo.setDrugName(drug.getName());
            if (temp.getOperationType() == 1) {
                stockRecordVo.setOperationName("入库");
            }
            if (temp.getOperationType() == 2) {
                stockRecordVo.setOperationName("入库");
            }
            stockRecordVos.add(stockRecordVo);
        }
        return stockRecordVos;
    }
}
