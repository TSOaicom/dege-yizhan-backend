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
public class ToiletParking    {
    @TableId(value = "toilet_parking_id", type = IdType.AUTO)
    @JsonProperty(value = "toiletParkingId")
    @Excel(name = "主键")

    private Integer toiletParkingId;
    @JsonProperty(value = "name")
    @Excel(name = "厕所名称")
    @ExcelProperty(value = "厕所名称")
    @TableField(value = "name")

    private String name;
    @JsonProperty(value = "latitude")
    @Excel(name = "纬度")
    @ExcelProperty(value = "纬度")
    @TableField(value = "latitude")

    private String latitude;
    @JsonProperty(value = "longitude")
    @Excel(name = "经度")
    @ExcelProperty(value = "经度")
    @TableField(value = "longitude")

    private String longitude;
    @JsonProperty(value = "hasParking")
    @Excel(name = "有免费车位")
    @ExcelProperty(value = "有免费车位")
    @TableField(value = "has_parking")

    private Boolean hasParking;
    @JsonProperty(value = "parkingCount")
    @Excel(name = "车位数量")
    @ExcelProperty(value = "车位数量")
    @TableField(value = "parking_count")

    private Integer parkingCount;
    @JsonProperty(value = "isOpen")
    @Excel(name = "开放状态")
    @ExcelProperty(value = "开放状态")
    @TableField(value = "is_open")

    private Boolean isOpen;


}
