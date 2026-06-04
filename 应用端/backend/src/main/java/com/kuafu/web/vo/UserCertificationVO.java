package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.kuafu.common.entity.StaticResource;
import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import com.kuafu.common.entity.BaseEntity;
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
public class UserCertificationVO  extends BaseEntity {

    @JsonProperty("userCertificationId")
    private Integer userCertificationId;
    @JsonProperty("userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty("statusEnumCertStatusEnumId1")
    private Integer statusEnumCertStatusEnumId1;
    @JsonProperty("remark")
    private String remark;


    private List<StaticResource> driverLicense;
    private List<StaticResource> vehicleLicense;
    private List<StaticResource> taxiLicense;


}
