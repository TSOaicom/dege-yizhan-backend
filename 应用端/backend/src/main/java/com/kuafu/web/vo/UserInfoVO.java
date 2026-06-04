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
 * <p>用户信息</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO  extends BaseEntity {

    @JsonProperty("userInfoId")
    private Integer userInfoId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("city")
    private String city;
    @JsonProperty("carPlate")
    private String carPlate;
    @JsonProperty("carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
    @JsonProperty("realName")
    private String realName;
    @JsonProperty("certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
    @JsonProperty("activeDays")
    private Integer activeDays;
    @JsonProperty("continueActiveDays")
    private Integer continueActiveDays;
    @JsonProperty("password")
    private String password;


    private List<StaticResource> avatar;


}
