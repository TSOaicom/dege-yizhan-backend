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
 * <p>  邀请记录 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("invite_record")
public class InviteRecord  {
    @TableId(value = "invite_record_id", type = IdType.AUTO)
    @JsonProperty("invite_record_id")
    
    
    
    private Integer inviteRecordId;
    @JsonProperty("user_info_user_info_id_1")
    @IsNotNullField(description = "邀请人")
    
    
    @TableField("user_info_user_info_id_1")
    private Integer userInfoUserInfoId1;
    @JsonProperty("invitee_phone")
    @IsNotNullField(description = "被邀请手机号")
    
    
    @TableField("invitee_phone")
    private String inviteePhone;
    @JsonProperty("invite_time")
    
    
    @TableField("invite_time")
    private Date inviteTime;





}
