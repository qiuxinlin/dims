package com.zx.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 库存信息
 *
 * @author zhangxi
 */
@Data
public class StockRecordVo {
    private Integer id;

    private Integer drugId;

    private String drugName;

    private Integer stockId;

    private Integer operationType;

    private String operationName;

    private Integer quantity;

    private String outbound;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
