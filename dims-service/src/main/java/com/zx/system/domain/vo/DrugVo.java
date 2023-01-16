package com.zx.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 药品信息
 *
 * @author zhangxi
 */
@Data
public class DrugVo {
    private Integer id;

    private String name;

    private String simpleCode;

    private String specification;

    private String manufacturerName;

    private Date manufactureDate;

    private Date exp;

    private Integer lot;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
