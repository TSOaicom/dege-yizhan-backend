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
public class UserCertificationAll {
    @TableField(value="uc.user_info_user_info_id_1")
    @JsonProperty("user_info_user_info_id_1")
    private Integer userInfoUserInfoId1;
    @TableField(value="ui.car_plate")
    @JsonProperty("car_plate")
    private String carPlate;
    @TableField(value="ui.cert_status_enum_cert_status_enum_id_1")
    @JsonProperty("cert_status_enum_cert_status_enum_id_1")
    private Integer certStatusEnumCertStatusEnumId1;
    @TableField(value="ui.city")
    @JsonProperty("city")
    private String city;
    @TableField(value="uc.remark")
    @JsonProperty("remark")
    private String remark;
    @TableField(value="ui.real_name")
    @JsonProperty("real_name")
    private String realName;
    @TableField(value="uc.user_certification_id")
    @JsonProperty("user_certification_id")
    private Integer userCertificationId;
    @TableField(value="ui.password")
    @JsonProperty("password")
    private String password;
    @TableField(value="uc.status_enum_cert_status_enum_id_1")
    @JsonProperty("status_enum_cert_status_enum_id_1")
    private Integer statusEnumCertStatusEnumId1;
    @TableField(value="ui.phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;
    @TableField(value="ui.continue_active_days")
    @JsonProperty("continue_active_days")
    private Integer continueActiveDays;
    @TableField(value="ui.car_type_enum_car_type_enum_id_1")
    @JsonProperty("car_type_enum_car_type_enum_id_1")
    private Integer carTypeEnumCarTypeEnumId1;
    @TableField(value="ui.active_days")
    @JsonProperty("active_days")
    private Integer activeDays;
    @TableField(value="cse.status")
    @JsonProperty("status")
    private String status;


@TableField(value="driver_license")
@JsonProperty("driver_license")
private List<StaticResource> driverLicense;
@TableField(value="vehicle_license")
@JsonProperty("vehicle_license")
private List<StaticResource> vehicleLicense;
@TableField(value="taxi_license")
@JsonProperty("taxi_license")
private List<StaticResource> taxiLicense;








@TableField(exist = false)
private List<StaticResource> avatar;

}
