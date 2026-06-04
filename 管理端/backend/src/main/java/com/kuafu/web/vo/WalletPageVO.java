package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class WalletPageVO extends PageRequest {

    @JsonProperty(value = "walletId")
    private Integer walletId;
    @JsonProperty(value = "userInfoUserInfoId1")
    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "balance")
    private Double balance;
    @JsonProperty(value = "memberTypeEnumMemberTypeEnumId1")
    private Integer memberTypeEnumMemberTypeEnumId1;
    @JsonProperty(value = "memberExpire")
    private Date memberExpire;

}
