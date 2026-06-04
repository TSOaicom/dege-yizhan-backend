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
public class HeatLevelEnum  {
    @TableId(value = "heat_level_enum_id", type = IdType.AUTO)
    @JsonProperty("heat_level_enum_id")
    
    
    
    private Integer heatLevelEnumId;
    @JsonProperty("level_name")
    @IsNotNullField(description = "等级名称")
    
    
    @TableField("level_name")
    private String levelName;
    @JsonProperty("color")
    @IsNotNullField(description = "颜色")
    
    
    @TableField("color")
    private String color;





}
