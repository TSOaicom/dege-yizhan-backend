package com.kuafu.web.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.kuafu.common.entity.StaticResource;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kuafu.web.annotation.IsNotNullField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.kuafu.common.annotation.*;
/**
 * <p>  钱包 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("wallet")
public class Wallet  {
    @TableId(value = "wallet_id", type = IdType.AUTO)
    @JsonProperty("wallet_id")
    
    
    
    private Integer walletId;
    @JsonProperty("user_info_user_info_id_1")
    @IsNotNullField(description = "用户ID")
    
    
    @TableField("user_info_user_info_id_1")
    private Integer userInfoUserInfoId1;
    @JsonProperty("balance")
    @IsNotNullField(description = "余额")
    
    
    @TableField("balance")
    private Double balance;
    @JsonProperty("member_type_enum_member_type_enum_id_1")
    @IsNotNullField(description = "会员类型")
    
    
    @TableField("member_type_enum_member_type_enum_id_1")
    private Integer memberTypeEnumMemberTypeEnumId1;
    @JsonProperty("member_expire")
    
    
    @TableField("member_expire")
    private Date memberExpire;





}
