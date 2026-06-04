package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * <p>邀请记录-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InviteRecordAllPageVO extends PageRequest {

    @JsonProperty(value = "userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "inviteTime")
    private Date inviteTime;
    @JsonProperty(value = "inviteRecordId")
    private Integer inviteRecordId;
    @JsonProperty(value = "certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "activeDays")
    private Integer activeDays;
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "avatarResourceKey")
    private String avatarResourceKey;
    @JsonProperty(value = "inviteePhone")
    private String inviteePhone;
    @JsonProperty(value = "carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
    @JsonProperty(value = "realName")
    private String realName;
    @JsonProperty(value = "continueActiveDays")
    private Integer continueActiveDays;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "carPlate")
    private String carPlate;

}
