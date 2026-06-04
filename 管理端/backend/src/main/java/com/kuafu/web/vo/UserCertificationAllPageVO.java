package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * <p>资质认证-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCertificationAllPageVO extends PageRequest {

    @JsonProperty(value = "userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "activeDays")
    private Integer activeDays;
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "remark")
    private String remark;
    @JsonProperty(value = "avatarResourceKey")
    private String avatarResourceKey;
    @JsonProperty(value = "carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
    @JsonProperty(value = "realName")
    private String realName;
    @JsonProperty(value = "vehicleLicenseResourceKey")
    private String vehicleLicenseResourceKey;
    @JsonProperty(value = "continueActiveDays")
    private Integer continueActiveDays;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "driverLicenseResourceKey")
    private String driverLicenseResourceKey;
    @JsonProperty(value = "carPlate")
    private String carPlate;
    @JsonProperty(value = "statusEnumCertStatusEnumId1")
    private Integer statusEnumCertStatusEnumId1;
    @JsonProperty(value = "userCertificationId")
    private Integer userCertificationId;
    @JsonProperty(value = "taxiLicenseResourceKey")
    private String taxiLicenseResourceKey;
    @JsonProperty(value = "status")
    private String status;

}
