package com.zx.web.controller.biz;

import com.zx.common.core.controller.BaseController;
import com.zx.common.core.domain.AjaxResult;
import com.zx.common.core.page.TableDataInfo;
import com.zx.system.domain.Drug;
import com.zx.system.domain.Manufacturer;
import com.zx.system.service.IDrugService;
import com.zx.system.service.IManufacturerService;
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
 * ManufacturerController
 *
 * @author zhangxi
 */
@RestController
@RequestMapping
public class ManufacturerController extends BaseController {
    @Autowired
    private IManufacturerService manufacturerService;

    @PostMapping("/manufacturer")
    public AjaxResult create(@RequestBody Manufacturer manufacturer) {
        Boolean result = manufacturerService.create(manufacturer);
        return success(result);
    }

    @GetMapping("/manufacturers/all")
    public AjaxResult findAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        return success(manufacturers);
    }

    @PostMapping("/manufacturers")
    public TableDataInfo findByParam(@RequestBody Manufacturer manufacturer) {
        startPage();
        List<Manufacturer> manufacturers = manufacturerService.findByParam(manufacturer);
        return getDataTable(manufacturers);
    }

    @GetMapping("/manufacturer/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        return success(manufacturer);
    }

    @PutMapping("/manufacturer")
    public AjaxResult update(@RequestBody Manufacturer manufacturer) {
        Boolean result = manufacturerService.update(manufacturer);
        return success(result);
    }

    @PostMapping("/manufacturer/delete")
    public AjaxResult deleteByIds(@RequestBody List<Integer> ids) {
        Boolean result = manufacturerService.deleteByIds(ids);
        return success(result);
    }
}
