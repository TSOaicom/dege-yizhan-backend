package com.kuafu.web.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.kuafu.common.entity.BaseEntity;
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
public class UserInfoPageVO extends BaseEntity {

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
