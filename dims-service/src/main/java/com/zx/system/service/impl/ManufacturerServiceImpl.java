package com.zx.system.service.impl;

import com.zx.common.core.domain.model.LoginUser;
import com.zx.common.utils.SecurityUtils;
import com.zx.system.domain.Manufacturer;
import com.zx.system.mapper.ManufacturerMapper;
import com.zx.system.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ManufacturerServiceImpl
 *
 * @author zhangxi
 */
@Service
public class ManufacturerServiceImpl implements IManufacturerService {
    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Override
    public Boolean create(Manufacturer manufacturer) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        manufacturer.setCreateBy(loginUser.getUsername());
        manufacturer.setCreateTime(new Date());
        int result = manufacturerMapper.create(manufacturer);
        return result > 0;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerMapper.findAll();
    }

    @Override
    public List<Manufacturer> findByParam(Manufacturer manufacturer) {
        return manufacturerMapper.findByParam(manufacturer);
    }

    @Override
    public Manufacturer findById(Integer id) {
        return manufacturerMapper.findById(id);
    }

    @Override
    public Boolean update(Manufacturer manufacturer) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        manufacturer.setUpdateBy(loginUser.getUsername());
        manufacturerMapper.update(manufacturer);
        return true;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        manufacturerMapper.deleteByIds(ids);
        return true;
    }
}
