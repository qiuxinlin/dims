package com.zx.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 药单信息
 *
 * @author zhangxi
 */
@Data
public class Order {
    private Integer id;

    private String visitNo;

    private Integer drugId;

    private Integer quantity;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
