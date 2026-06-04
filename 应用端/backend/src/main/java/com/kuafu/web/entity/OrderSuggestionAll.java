package com.kuafu.web.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
public class OrderSuggestionAll {
    @TableField(value="os.area_name")
    @JsonProperty("area_name")
    private String areaName;
    @TableField(value="os.order_suggestion_id")
    @JsonProperty("order_suggestion_id")
    private Integer orderSuggestionId;
    @TableField(value="hle.level_name")
    @JsonProperty("level_name")
    private String levelName;
    @TableField(value="os.order_count")
    @JsonProperty("order_count")
    private Integer orderCount;
    @TableField(value="os.distance")
    @JsonProperty("distance")
    private String distance;
    @TableField(value="hle.color")
    @JsonProperty("color")
    private String color;
    @TableField(value="os.heat_level_enum_heat_level_enum_id_1")
    @JsonProperty("heat_level_enum_heat_level_enum_id_1")
    private Integer heatLevelEnumHeatLevelEnumId1;
    @TableField(value="os.suggest_direction")
    @JsonProperty("suggest_direction")
    private String suggestDirection;
    @TableField(value="os.recommend_index")
    @JsonProperty("recommend_index")
    private String recommendIndex;










}
