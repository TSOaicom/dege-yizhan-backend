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
 * <p>钱包-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WalletAllPageVO extends BaseEntity {

@JsonProperty("typeName")
    private String typeName;
@JsonProperty("userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
@JsonProperty("carPlate")
    private String carPlate;
@JsonProperty("certStatusEnumCertStatusEnumId1")
    private Integer certStatusEnumCertStatusEnumId1;
@JsonProperty("city")
    private String city;
@JsonProperty("realName")
    private String realName;
@JsonProperty("memberTypeEnumMemberTypeEnumId1")
    private Integer memberTypeEnumMemberTypeEnumId1;
@JsonProperty("walletId")
    private Integer walletId;
@JsonProperty("password")
    private String password;
@JsonProperty("balance")
    private Double balance;
    @JsonDeserialize(using = MultiDateDeserializer.class)
@JsonProperty("memberExpire")
    private Date memberExpire;
@JsonProperty("phoneNumber")
    private String phoneNumber;
@JsonProperty("continueActiveDays")
    private Integer continueActiveDays;
@JsonProperty("carTypeEnumCarTypeEnumId1")
    private Integer carTypeEnumCarTypeEnumId1;
@JsonProperty("activeDays")
    private Integer activeDays;

}
