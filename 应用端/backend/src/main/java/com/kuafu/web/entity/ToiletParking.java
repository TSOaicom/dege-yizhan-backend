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
 * <p>  厕所停车 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("toilet_parking")
public class ToiletParking  {
    @TableId(value = "toilet_parking_id", type = IdType.AUTO)
    @JsonProperty("toilet_parking_id")
    
    
    
    private Integer toiletParkingId;
    @JsonProperty("name")
    @IsNotNullField(description = "厕所名称")
    
    
    @TableField("name")
    private String name;
    @JsonProperty("latitude")
    @IsNotNullField(description = "纬度")
    
    
    @TableField("latitude")
    private String latitude;
    @JsonProperty("longitude")
    @IsNotNullField(description = "经度")
    
    
    @TableField("longitude")
    private String longitude;
    @JsonProperty("has_parking")
    @IsNotNullField(description = "有免费车位")
    
    
    @TableField("has_parking")
    private Boolean hasParking;
    @JsonProperty("parking_count")
    
    
    @TableField("parking_count")
    private Integer parkingCount;
    @JsonProperty("is_open")
    @IsNotNullField(description = "开放状态")
    
    
    @TableField("is_open")
    private Boolean isOpen;





}
