package com.kuafu.web.entity;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuafu.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;
import com.kuafu.common.annotation.Excel.*;



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
public class Wallet    {
    @TableId(value = "wallet_id", type = IdType.AUTO)
    @JsonProperty(value = "walletId")
    @Excel(name = "主键")

    private Integer walletId;
    @JsonProperty(value = "userInfoUserInfoId1")
    @Excel(name = "用户ID")
    @ExcelProperty(value = "用户ID")
    @TableField(value = "user_info_user_info_id_1")

    private Integer userInfoUserInfoId1;
    @JsonProperty(value = "balance")
    @Excel(name = "余额")
    @ExcelProperty(value = "余额")
    @TableField(value = "balance")

    private Double balance;
    @JsonProperty(value = "memberTypeEnumMemberTypeEnumId1")
    @Excel(name = "会员类型")
    @ExcelProperty(value = "会员类型")
    @TableField(value = "member_type_enum_member_type_enum_id_1")

    private Integer memberTypeEnumMemberTypeEnumId1;
    @JsonProperty(value = "memberExpire")
    @Excel(name = "会员到期")
    @ExcelProperty(value = "会员到期")
    @TableField(value = "member_expire")

    private Date memberExpire;


}
