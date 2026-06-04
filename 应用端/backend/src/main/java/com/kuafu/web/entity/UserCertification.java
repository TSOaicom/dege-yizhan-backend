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
 * <p>  资质认证 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_certification")
public class UserCertification  {
    @TableId(value = "user_certification_id", type = IdType.AUTO)
    @JsonProperty("user_certification_id")
    
    
    
    private Integer userCertificationId;
    @JsonProperty("user_info_user_info_id_1")
    @IsNotNullField(description = "用户ID")
    
    
    @TableField("user_info_user_info_id_1")
    private Integer userInfoUserInfoId1;
    @JsonProperty("status_enum_cert_status_enum_id_1")
    @IsNotNullField(description = "审核状态")
    
    
    @TableField("status_enum_cert_status_enum_id_1")
    private Integer statusEnumCertStatusEnumId1;
    @JsonProperty("remark")
    
    
    @TableField("remark")
    private String remark;



@TableField(exist = false)
@JsonProperty("driver_license")
private List<StaticResource> driverLicense;

@TableField(exist = false)
@JsonProperty("vehicle_license")
private List<StaticResource> vehicleLicense;

@TableField(exist = false)
@JsonProperty("taxi_license")
private List<StaticResource> taxiLicense;



}
