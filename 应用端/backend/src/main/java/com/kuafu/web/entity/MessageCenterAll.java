package com.kuafu.web.entity;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



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
public class MessageCenterAll {
    @TableField(value="mc.is_read")
    @JsonProperty("is_read")
    private Boolean isRead;
    @TableField(value="mte.type_name")
    @JsonProperty("type_name")
    private String typeName;
    @TableField(value="mc.message_center_id")
    @JsonProperty("message_center_id")
    private Integer messageCenterId;
    @TableField(value="mc.create_time")
    @JsonProperty("create_time")
    private Date createTime;
    @TableField(value="mc.type_enum_message_type_enum_id_1")
    @JsonProperty("type_enum_message_type_enum_id_1")
    private Integer typeEnumMessageTypeEnumId1;
    @TableField(value="mc.title")
    @JsonProperty("title")
    private String title;
    @TableField(value="mc.content")
    @JsonProperty("content")
    private String content;










}
