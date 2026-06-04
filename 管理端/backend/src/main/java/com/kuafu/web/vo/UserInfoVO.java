package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

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
public class UserInfoVO  {

     @JsonProperty(value = "userInfoId")
    private Integer userInfoId;
     @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
     @JsonProperty(value = "city")
    private String city;
     @JsonProperty(value = "carPlate")
    private String carPlate;
     @JsonProperty(value = "carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
     @JsonProperty(value = "avatarResourceKey")
    private String avatarResourceKey;
     @JsonProperty(value = "realName")
    private String realName;
     @JsonProperty(value = "certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
     @JsonProperty(value = "activeDays")
    private Integer activeDays;
     @JsonProperty(value = "continueActiveDays")
    private Integer continueActiveDays;
     @JsonProperty(value = "password")
    private String password;

    @JsonProperty("avatar")
    private List<StaticResource> avatar;

}
