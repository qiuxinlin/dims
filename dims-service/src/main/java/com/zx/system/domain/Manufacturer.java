package com.zx.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 生产商信息
 *
 * @author zhangxi
 */
@Data
public class Manufacturer {
    private Integer id;

    private String name;

    private String address;

    private Integer postCode;

    private String phone;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
