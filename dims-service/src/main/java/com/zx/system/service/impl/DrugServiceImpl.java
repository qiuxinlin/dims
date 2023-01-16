package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Drug;
import com.zx.system.domain.Manufacturer;
import com.zx.system.domain.Stock;
import com.zx.system.domain.vo.DrugVo;
import com.zx.system.mapper.DrugMapper;
import com.zx.system.mapper.ManufacturerMapper;
import com.zx.system.mapper.StockMapper;
import com.zx.system.service.IDrugService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * IDrugService
 *
 * @author zhangxi
 */
@Service
public class DrugServiceImpl implements IDrugService {
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private ManufacturerMapper manufacturerMapper;
    @Autowired
    private StockMapper stockMapper;

    @Override
    public Boolean create(Drug drug) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        drug.setCreateBy(loginUser.getUsername());
        drug.setCreateTime(new Date());
        int result = drugMapper.create(drug);
        return result > 0;
    }

    @Override
    public List<DrugVo> findByParam(Drug drug) {
        List<DrugVo> drugVos = new ArrayList<>();
        List<Drug> drugs = drugMapper.findByParam(drug);
        List<Integer> drugIds = drugs.stream().map(Drug::getId).collect(Collectors.toList());
        List<Stock> stocks = stockMapper.findByDrugIds(drugIds);
        Map<Integer, Stock> stockMap = stocks.stream().collect(Collectors.toMap(Stock::getDrugId, Stock -> Stock));
        List<Manufacturer> manufacturers = manufacturerMapper.findAll();
        Map<Integer, Manufacturer> manufacturerMap = manufacturers.stream().collect(
                Collectors.toMap(Manufacturer::getId, Manufacturer -> Manufacturer));
        for (Drug temp : drugs) {
            DrugVo drugVo = new DrugVo();
            BeanUtils.copyProperties(temp, drugVo);
            Stock stock = stockMap.get(temp.getId());
            if (null != stock) {
                drugVo.setStock(stock.getQuantity());
            }
            drugVo.setManufacturerName(manufacturerMap.get(temp.getManufacturerId()).getName());
            drugVos.add(drugVo);
        }
        return drugVos;
    }

    @Override
    public Drug findById(Integer id) {
        return drugMapper.findById(id);
    }

    @Override
    public Boolean update(Drug drug) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        drug.setUpdateBy(loginUser.getUsername());
        drugMapper.update(drug);
        return true;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        drugMapper.deleteByIds(ids);
        return true;
    }
}
