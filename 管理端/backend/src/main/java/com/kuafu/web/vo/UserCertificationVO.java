package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

/**
 * <p>资质认证</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCertificationVO  {

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

    @JsonProperty("driverLicense")
    private List<StaticResource> driverLicense;
    @JsonProperty("vehicleLicense")
    private List<StaticResource> vehicleLicense;
    @JsonProperty("taxiLicense")
    private List<StaticResource> taxiLicense;

}
