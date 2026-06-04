package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>用户信息-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoPageVO extends PageRequest {

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

}
