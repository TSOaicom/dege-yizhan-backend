package com.kuafu.web.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.kuafu.common.entity.StaticResource;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kuafu.web.annotation.IsNotNullField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.kuafu.common.annotation.*;
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
public class OrderSuggestion  {
    @TableId(value = "order_suggestion_id", type = IdType.AUTO)
    @JsonProperty("order_suggestion_id")
    
    
    
    private Integer orderSuggestionId;
    @JsonProperty("area_name")
    @IsNotNullField(description = "区域名称")
    
    
    @TableField("area_name")
    private String areaName;
    @JsonProperty("order_count")
    @IsNotNullField(description = "预估订单量")
    
    
    @TableField("order_count")
    private Integer orderCount;
    @JsonProperty("distance")
    @IsNotNullField(description = "距离(公里)")
    
    
    @TableField("distance")
    private String distance;
    @JsonProperty("recommend_index")
    @IsNotNullField(description = "推荐指数")
    
    
    @TableField("recommend_index")
    private String recommendIndex;
    @JsonProperty("suggest_direction")
    @IsNotNullField(description = "建议方向")
    
    
    @TableField("suggest_direction")
    private String suggestDirection;
    @JsonProperty("heat_level_enum_heat_level_enum_id_1")
    @IsNotNullField(description = "热力等级")
    
    
    @TableField("heat_level_enum_heat_level_enum_id_1")
    private Integer heatLevelEnumHeatLevelEnumId1;





}
