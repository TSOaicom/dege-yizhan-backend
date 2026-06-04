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
public class UserCertification    {
    @TableId(value = "user_certification_id", type = IdType.AUTO)
    @JsonProperty(value = "userCertificationId")
    @Excel(name = "主键")

    private Integer userCertificationId;
    @JsonProperty(value = "userInfoUserInfoId1")
    @Excel(name = "用户ID")
    @ExcelProperty(value = "用户ID")
    @TableField(value = "user_info_user_info_id_1")

    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "driverLicenseResourceKey")
    @Excel(name = "驾驶证")
    @ExcelProperty(value = "驾驶证")
    @TableField(value = "driver_license_resource_key")

    private String driverLicenseResourceKey;
    @JsonProperty(value = "vehicleLicenseResourceKey")
    @Excel(name = "行驶证")
    @ExcelProperty(value = "行驶证")
    @TableField(value = "vehicle_license_resource_key")

    private String vehicleLicenseResourceKey;
    @JsonProperty(value = "taxiLicenseResourceKey")
    @Excel(name = "营运证")
    @ExcelProperty(value = "营运证")
    @TableField(value = "taxi_license_resource_key")

    private String taxiLicenseResourceKey;
    @JsonProperty(value = "statusEnumCertStatusEnumId1")
    @Excel(name = "审核状态")
    @ExcelProperty(value = "审核状态")
    @TableField(value = "status_enum_cert_status_enum_id_1")

    private Integer statusEnumCertStatusEnumId1;
    @JsonProperty(value = "remark")
    @Excel(name = "审核备注")
    @ExcelProperty(value = "审核备注")
    @TableField(value = "remark")

    private String remark;

    @TableField(exist = false)
    private List<StaticResource> driverLicense;
    @TableField(exist = false)
    private List<StaticResource> vehicleLicense;
    @TableField(exist = false)
    private List<StaticResource> taxiLicense;

}
