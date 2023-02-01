package com.zx.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 药单信息
 *
 * @author zhangxi
 */
@Data
public class OrderVo {
    private Integer id;

    private String visitNo;

    private Integer drugId;

    private String drugName;

    private Integer quantity;

    private Integer status;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
