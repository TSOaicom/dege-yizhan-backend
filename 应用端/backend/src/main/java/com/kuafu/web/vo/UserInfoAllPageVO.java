package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import com.kuafu.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.kuafu.common.entity.StaticResource;

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
public class UserInfoAllPageVO extends BaseEntity {

@JsonProperty("password")
    private String password;
@JsonProperty("typeName")
    private String typeName;
@JsonProperty("carPlate")
    private String carPlate;
@JsonProperty("certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
@JsonProperty("city")
    private String city;
@JsonProperty("phoneNumber")
    private String phoneNumber;
@JsonProperty("realName")
    private String realName;
@JsonProperty("continueActiveDays")
    private Integer continueActiveDays;
@JsonProperty("userInfoId")
    private Integer userInfoId;
@JsonProperty("carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
@JsonProperty("activeDays")
    private Integer activeDays;
@JsonProperty("status")
    private String status;

}
