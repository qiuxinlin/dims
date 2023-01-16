package com.zx.system.mapper;

import com.zx.system.domain.Drug;

import java.util.List;

/**
 * DrugMapper
 *
 * @author zhangxi
 */
public interface DrugMapper {
    int create(Drug drug);

    List<Drug> searchAll();

    List<Drug> findByParam(Drug drug);

    Drug findById(Integer id);

    int update(Drug drug);

    int deleteByIds(List<Integer> ids);
}
