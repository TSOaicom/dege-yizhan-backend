package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class UserCertificationPageVO extends PageRequest {

    @JsonProperty(value = "userCertificationId")
    private Integer userCertificationId;
    @JsonProperty(value = "userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "driverLicenseResourceKey")
    private String driverLicenseResourceKey;
    @JsonProperty(value = "vehicleLicenseResourceKey")
    private String vehicleLicenseResourceKey;
    @JsonProperty(value = "taxiLicenseResourceKey")
    private String taxiLicenseResourceKey;
    @JsonProperty(value = "statusEnumCertStatusEnumId1")
    private Integer statusEnumCertStatusEnumId1;
    @JsonProperty(value = "remark")
    private String remark;

}
