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
 import  com.kuafu.web.event.BaseLoginVo; 


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
public class UserInfo   extends BaseLoginVo   {
    @TableId(value = "user_info_id", type = IdType.AUTO)
    @JsonProperty(value = "userInfoId")
    @Excel(name = "主键")

    private Integer userInfoId;
    @JsonProperty(value = "phoneNumber")
    @Excel(name = "手机号")
    @ExcelProperty(value = "手机号")
    @TableField(value = "phone_number")

    private String phoneNumber;
    @JsonProperty(value = "city")
    @Excel(name = "城市")
    @ExcelProperty(value = "城市")
    @TableField(value = "city")

    private String city;
    @JsonProperty(value = "carPlate")
    @Excel(name = "车牌号")
    @ExcelProperty(value = "车牌号")
    @TableField(value = "car_plate")

    private String carPlate;
    @JsonProperty(value = "carTypeEnumCarTypeEnumId1")
    @Excel(name = "车辆类型")
    @ExcelProperty(value = "车辆类型")
    @TableField(value = "car_type_enum_car_type_enum_id_1")

    private Integer carTypeEnumCarTypeEnumId1;
    @JsonProperty(value = "avatarResourceKey")
    @Excel(name = "头像")
    @ExcelProperty(value = "头像")
    @TableField(value = "avatar_resource_key")

    private String avatarResourceKey;
    @JsonProperty(value = "realName")
    @Excel(name = "姓名")
    @ExcelProperty(value = "姓名")
    @TableField(value = "real_name")

    private String realName;
    @JsonProperty(value = "certStatusEnumCertStatusEnumId1")
    @Excel(name = "认证状态")
    @ExcelProperty(value = "认证状态")
    @TableField(value = "cert_status_enum_cert_status_enum_id_1")

    private Integer certStatusEnumCertStatusEnumId1;
    @JsonProperty(value = "activeDays")
    @Excel(name = "累计活跃天数")
    @ExcelProperty(value = "累计活跃天数")
    @TableField(value = "active_days")

    private Integer activeDays;
    @JsonProperty(value = "continueActiveDays")
    @Excel(name = "连续活跃天数")
    @ExcelProperty(value = "连续活跃天数")
    @TableField(value = "continue_active_days")

    private Integer continueActiveDays;
    @JsonProperty(value = "password")
    @Excel(name = "密码")
    @ExcelProperty(value = "密码")
    @TableField(value = "password")

    private String password;

    @TableField(exist = false)
    private List<StaticResource> avatar;

}
