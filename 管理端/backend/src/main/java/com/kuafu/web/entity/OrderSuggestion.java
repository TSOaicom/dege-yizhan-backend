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
 * <p>  订单建议 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_suggestion")
public class OrderSuggestion    {
    @TableId(value = "order_suggestion_id", type = IdType.AUTO)
    @JsonProperty(value = "orderSuggestionId")
    @Excel(name = "主键")

    private Integer orderSuggestionId;
    @JsonProperty(value = "areaName")
    @Excel(name = "区域名称")
    @ExcelProperty(value = "区域名称")
    @TableField(value = "area_name")

    private String areaName;
    @JsonProperty(value = "orderCount")
    @Excel(name = "预估订单量")
    @ExcelProperty(value = "预估订单量")
    @TableField(value = "order_count")

    private Integer orderCount;
    @JsonProperty(value = "distance")
    @Excel(name = "距离(公里)")
    @ExcelProperty(value = "距离(公里)")
    @TableField(value = "distance")

    private String distance;
    @JsonProperty(value = "recommendIndex")
    @Excel(name = "推荐指数")
    @ExcelProperty(value = "推荐指数")
    @TableField(value = "recommend_index")

    private String recommendIndex;
    @JsonProperty(value = "suggestDirection")
    @Excel(name = "建议方向")
    @ExcelProperty(value = "建议方向")
    @TableField(value = "suggest_direction")

    private String suggestDirection;
    @JsonProperty(value = "heatLevelEnumHeatLevelEnumId1")
    @Excel(name = "热力等级")
    @ExcelProperty(value = "热力等级")
    @TableField(value = "heat_level_enum_heat_level_enum_id_1")

    private Integer heatLevelEnumHeatLevelEnumId1;


}
