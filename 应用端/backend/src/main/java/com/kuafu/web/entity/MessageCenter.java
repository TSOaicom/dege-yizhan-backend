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
 * <p>  消息中心 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("message_center")
public class MessageCenter  {
    @TableId(value = "message_center_id", type = IdType.AUTO)
    @JsonProperty("message_center_id")
    
    
    
    private Integer messageCenterId;
    @JsonProperty("title")
    @IsNotNullField(description = "标题")
    
    
    @TableField("title")
    private String title;
    @JsonProperty("content")
    @IsNotNullField(description = "内容")
    
    
    @TableField("content")
    private String content;
    @JsonProperty("type_enum_message_type_enum_id_1")
    @IsNotNullField(description = "消息类型")
    
    
    @TableField("type_enum_message_type_enum_id_1")
    private Integer typeEnumMessageTypeEnumId1;
    @JsonProperty("is_read")
    @IsNotNullField(description = "已读状态")
    
    
    @TableField("is_read")
    private Boolean isRead;
    @JsonProperty("create_time")
    @TableField(fill = FieldFill.INSERT)
    
    
    private Date createTime;





}
