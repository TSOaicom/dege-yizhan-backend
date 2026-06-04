package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

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
public class WalletVO  {

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
