package com.kuafu.web.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuafu.common.annotation.Excel;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kuafu.common.annotation.Excel.*;

/**
 * <p>  邀请记录 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteRecordAll  {
    @TableField(value="ir.user_info_user_info_id_1")
    @JsonProperty(value = "userInfoUserInfoId1")
    @Excel(name = "邀请人")
    private Integer userInfoUserInfoId1;
    @TableField(value="ir.invite_time")
    @JsonProperty(value = "inviteTime")
    @Excel(dateFormat = "yyyy-MM-dd HH:mm:ss", name = "邀请时间")
    private Date inviteTime;
    @TableField(value="ir.invite_record_id")
    @JsonProperty(value = "inviteRecordId")
    @Excel(name = "主键")
    private Integer inviteRecordId;
    @TableField(value="ui.cert_status_enum_cert_status_enum_id_1")
    @JsonProperty(value = "certStatusEnumCertStatusEnumId1")
    @Excel(name = "认证状态")
    private Integer certStatusEnumCertStatusEnumId1;
    @TableField(value="ui.city")
    @JsonProperty(value = "city")
    @Excel(name = "城市")
    private String city;
    @TableField(value="ui.active_days")
    @JsonProperty(value = "activeDays")
    @Excel(name = "累计活跃天数")
    private Integer activeDays;
    @TableField(value="ui.phone_number")
    @JsonProperty(value = "phoneNumber")
    @Excel(name = "手机号")
    private String phoneNumber;
    @TableField(value="ui.avatar_resource_key")
    @JsonProperty(value = "avatarResourceKey")
    @Excel(name = "头像")
    private String avatarResourceKey;
    @TableField(value="ir.invitee_phone")
    @JsonProperty(value = "inviteePhone")
    @Excel(name = "被邀请手机号")
    private String inviteePhone;
    @TableField(value="ui.car_type_enum_car_type_enum_id_1")
    @JsonProperty(value = "carTypeEnumCarTypeEnumId1")
    @Excel(name = "车辆类型")
    private Integer carTypeEnumCarTypeEnumId1;
    @TableField(value="ui.real_name")
    @JsonProperty(value = "realName")
    @Excel(name = "姓名")
    private String realName;
    @TableField(value="ui.continue_active_days")
    @JsonProperty(value = "continueActiveDays")
    @Excel(name = "连续活跃天数")
    private Integer continueActiveDays;
    @TableField(value="ui.password")
    @JsonProperty(value = "password")
    @Excel(name = "密码")
    private String password;
    @TableField(value="ui.car_plate")
    @JsonProperty(value = "carPlate")
    @Excel(name = "车牌号")
    private String carPlate;

}
