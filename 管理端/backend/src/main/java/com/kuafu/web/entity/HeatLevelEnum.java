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
 * <p>  热力等级 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("heat_level_enum")
public class HeatLevelEnum    {
    @TableId(value = "heat_level_enum_id", type = IdType.AUTO)
    @JsonProperty(value = "heatLevelEnumId")
    @Excel(name = "主键")

    private Integer heatLevelEnumId;
    @JsonProperty(value = "levelName")
    @Excel(name = "等级名称")
    @ExcelProperty(value = "等级名称")
    @TableField(value = "level_name")

    private String levelName;
    @JsonProperty(value = "color")
    @Excel(name = "颜色")
    @ExcelProperty(value = "颜色")
    @TableField(value = "color")

    private String color;


}
