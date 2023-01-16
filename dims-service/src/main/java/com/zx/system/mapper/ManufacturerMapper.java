package com.zx.system.mapper;

import com.zx.system.domain.Manufacturer;

import java.util.List;

/**
 * ManufacturerMapper
 *
 * @author zhangxi
 */
public interface ManufacturerMapper {
    int create(Manufacturer manufacturer);

    List<Manufacturer> findAll();

    List<Manufacturer> findByParam(Manufacturer manufacturer);

    Manufacturer findById(Integer id);

    int update(Manufacturer manufacturer);

    int deleteByIds(List<Integer> ids);
}
