package com.kuafu.web.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuafu.common.annotation.Excel;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class OrderSuggestionAll  {
    @TableField(value="os.suggest_direction")
    @JsonProperty(value = "suggestDirection")
    @Excel(name = "建议方向")
    private String suggestDirection;
    @TableField(value="os.area_name")
    @JsonProperty(value = "areaName")
    @Excel(name = "区域名称")
    private String areaName;
    @TableField(value="os.distance")
    @JsonProperty(value = "distance")
    @Excel(name = "距离(公里)")
    private String distance;
    @TableField(value="hle.color")
    @JsonProperty(value = "color")
    @Excel(name = "颜色")
    private String color;
    @TableField(value="os.heat_level_enum_heat_level_enum_id_1")
    @JsonProperty(value = "heatLevelEnumHeatLevelEnumId1")
    @Excel(name = "热力等级")
    private Integer heatLevelEnumHeatLevelEnumId1;
    @TableField(value="os.recommend_index")
    @JsonProperty(value = "recommendIndex")
    @Excel(name = "推荐指数")
    private String recommendIndex;
    @TableField(value="os.order_suggestion_id")
    @JsonProperty(value = "orderSuggestionId")
    @Excel(name = "主键")
    private Integer orderSuggestionId;
    @TableField(value="hle.level_name")
    @JsonProperty(value = "levelName")
    @Excel(name = "等级名称")
    private String levelName;
    @TableField(value="os.order_count")
    @JsonProperty(value = "orderCount")
    @Excel(name = "预估订单量")
    private Integer orderCount;

}
