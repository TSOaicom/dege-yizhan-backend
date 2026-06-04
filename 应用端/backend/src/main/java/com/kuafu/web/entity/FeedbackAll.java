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
 * <p>  反馈 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAll {
    @TableField(value="f.user_info_user_info_id_1")
    @JsonProperty("user_info_user_info_id_1")
    private Integer userInfoUserInfoId1;
    @TableField(value="ui.car_plate")
    @JsonProperty("car_plate")
    private String carPlate;
    @TableField(value="ui.cert_status_enum_cert_status_enum_id_1")
    @JsonProperty("cert_status_enum_cert_status_enum_id_1")
    private Integer certStatusEnumCertStatusEnumId1;
    @TableField(value="f.create_time")
    @JsonProperty("create_time")
    private Date createTime;
    @TableField(value="ui.city")
    @JsonProperty("city")
    private String city;
    @TableField(value="f.feedback_id")
    @JsonProperty("feedback_id")
    private Integer feedbackId;
    @TableField(value="ui.real_name")
    @JsonProperty("real_name")
    private String realName;
    @TableField(value="f.content")
    @JsonProperty("content")
    private String content;
    @TableField(value="ui.password")
    @JsonProperty("password")
    private String password;
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










@TableField(exist = false)
private List<StaticResource> avatar;

}
