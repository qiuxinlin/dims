package com.zx.system.service;

import com.zx.system.domain.Drug;
import com.zx.system.domain.vo.DrugVo;

import java.util.List;

/**
 * IDrugService
 *
 * @author zhangxi
 */
public interface IDrugService {

    Boolean create(Drug drug);

    List<DrugVo> findByParam(Drug drug);

    Drug findById(Integer id);

    Boolean update(Drug drug);

    Boolean deleteByIds(List<Integer> ids);
}
