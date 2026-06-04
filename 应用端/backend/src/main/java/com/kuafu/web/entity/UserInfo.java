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
 * <p>  用户信息 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo  {
    @TableId(value = "user_info_id", type = IdType.AUTO)
    @JsonProperty("user_info_id")
    
    
    
    private Integer userInfoId;
    @JsonProperty("phone_number")
    @IsNotNullField(description = "手机号")
    
    
    @TableField("phone_number")
    private String phoneNumber;
    @JsonProperty("city")
    @IsNotNullField(description = "城市")
    
    
    @TableField("city")
    private String city;
    @JsonProperty("car_plate")
    @IsNotNullField(description = "车牌号")
    
    
    @TableField("car_plate")
    private String carPlate;
    @JsonProperty("car_type_enum_car_type_enum_id_1")
    @IsNotNullField(description = "车辆类型")
    
    
    @TableField("car_type_enum_car_type_enum_id_1")
    private Integer carTypeEnumCarTypeEnumId1;
    @JsonProperty("real_name")
    
    
    @TableField("real_name")
    private String realName;
    @JsonProperty("cert_status_enum_cert_status_enum_id_1")
    @IsNotNullField(description = "认证状态")
    
    
    @TableField("cert_status_enum_cert_status_enum_id_1")
    private Integer certStatusEnumCertStatusEnumId1;
    @JsonProperty("active_days")
    
    
    @TableField("active_days")
    private Integer activeDays;
    @JsonProperty("continue_active_days")
    
    
    @TableField("continue_active_days")
    private Integer continueActiveDays;
    @JsonProperty("password")
    
    
    @TableField("password")
    private String password;



@TableField(exist = false)
@JsonProperty("avatar")
private List<StaticResource> avatar;



}
