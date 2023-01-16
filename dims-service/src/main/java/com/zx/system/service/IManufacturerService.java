package com.zx.system.service;

import com.zx.system.domain.Manufacturer;

import java.util.List;

/**
 * IManufacturerService
 *
 * @author zhangxi
 */
public interface IManufacturerService {

    Boolean create(Manufacturer manufacturer);

    List<Manufacturer> findAll();

    List<Manufacturer> findByParam(Manufacturer manufacturer);

    Manufacturer findById(Integer id);

    Boolean update(Manufacturer manufacturer);

    Boolean deleteByIds(List<Integer> ids);
}
