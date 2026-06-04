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
 * <p>钱包</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletVO  extends BaseEntity {

    @JsonProperty("walletId")
    private Integer walletId;
    @JsonProperty("userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty("balance")
    private Double balance;
    @JsonProperty("memberTypeEnumMemberTypeEnumId1")
    private Integer memberTypeEnumMemberTypeEnumId1;
    @JsonDeserialize(using = MultiDateDeserializer.class)
    @JsonProperty("memberExpire")
    private Date memberExpire;




}
