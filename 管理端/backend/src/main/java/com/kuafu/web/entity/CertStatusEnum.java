package com.kuafu.web.entity;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuafu.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;
import com.kuafu.common.annotation.Excel.*;



/**
 * <p>  认证状态 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("cert_status_enum")
public class CertStatusEnum    {
    @TableId(value = "cert_status_enum_id", type = IdType.AUTO)
    @JsonProperty(value = "certStatusEnumId")
    @Excel(name = "主键")

    private Integer certStatusEnumId;
    @JsonProperty(value = "status")
    @Excel(name = "状态")
    @ExcelProperty(value = "状态")
    @TableField(value = "status")

    private String status;


}
