package com.zx.web.controller.biz;

import com.zx.common.core.controller.BaseController;
import com.zx.common.core.domain.AjaxResult;
import com.zx.common.core.page.TableDataInfo;
import com.zx.system.domain.Drug;
import com.zx.system.domain.vo.DrugVo;
import com.zx.system.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DrugController
 *
 * @author zhangxi
 */
@RestController
@RequestMapping
public class DrugController extends BaseController {
    @Autowired
    private IDrugService drugService;

    @PostMapping("/drug")
    public AjaxResult create(@RequestBody Drug drug) {
        Boolean result = drugService.create(drug);
        return success(result);
    }

    @PostMapping("/drugs")
    public TableDataInfo findByParam(@RequestBody Drug drug) {
        startPage();
        List<DrugVo> drugs = drugService.findByParam(drug);
        return getDataTable(drugs);
    }

    @PostMapping("/drugs/all")
    public AjaxResult findAllByParam(@RequestBody Drug drug) {
        List<DrugVo> drugs = drugService.findByParam(drug);
        return success(drugs);
    }

    @GetMapping("/drug/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Drug drug = drugService.findById(id);
        return success(drug);
    }

    @PutMapping("/drug")
    public AjaxResult update(@RequestBody Drug drug) {
        Boolean result = drugService.update(drug);
        return success(result);
    }

    @PostMapping("/drugs/delete")
    public AjaxResult deleteByIds(@RequestBody List<Integer> ids) {
        Boolean result = drugService.deleteByIds(ids);
        return success(result);
    }
}
