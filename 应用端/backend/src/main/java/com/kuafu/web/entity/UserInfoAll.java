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
public class UserInfoAll {
    @TableField(value="ui.password")
    @JsonProperty("password")
    private String password;
    @TableField(value="cte.type_name")
    @JsonProperty("type_name")
    private String typeName;
    @TableField(value="ui.car_plate")
    @JsonProperty("car_plate")
    private String carPlate;
    @TableField(value="ui.cert_status_enum_cert_status_enum_id_1")
    @JsonProperty("cert_status_enum_cert_status_enum_id_1")
    private Integer certStatusEnumCertStatusEnumId1;
    @TableField(value="ui.city")
    @JsonProperty("city")
    private String city;
    @TableField(value="ui.phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;
    @TableField(value="ui.real_name")
    @JsonProperty("real_name")
    private String realName;
    @TableField(value="ui.continue_active_days")
    @JsonProperty("continue_active_days")
    private Integer continueActiveDays;
    @TableField(value="ui.user_info_id")
    @JsonProperty("user_info_id")
    private Integer userInfoId;
    @TableField(value="ui.car_type_enum_car_type_enum_id_1")
    @JsonProperty("car_type_enum_car_type_enum_id_1")
    private Integer carTypeEnumCarTypeEnumId1;
    @TableField(value="ui.active_days")
    @JsonProperty("active_days")
    private Integer activeDays;
    @TableField(value="cse.status")
    @JsonProperty("status")
    private String status;


@TableField(value="avatar")
@JsonProperty("avatar")
private List<StaticResource> avatar;








}
