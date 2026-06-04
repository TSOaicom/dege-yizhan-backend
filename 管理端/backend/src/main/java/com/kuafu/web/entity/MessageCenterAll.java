package com.kuafu.web.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuafu.common.annotation.Excel;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kuafu.common.annotation.Excel.*;

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
public class MessageCenterAll  {
    @TableField(value="mc.create_time")
    @JsonProperty(value = "createTime")
    @Excel(dateFormat = "yyyy-MM-dd HH:mm:ss", name = "创建时间")
    private Date createTime;
    @TableField(value="mc.type_enum_message_type_enum_id_1")
    @JsonProperty(value = "typeEnumMessageTypeEnumId1")
    @Excel(name = "消息类型")
    private Integer typeEnumMessageTypeEnumId1;
    @TableField(value="mc.is_read")
    @JsonProperty(value = "isRead")
    @Excel(name = "已读状态")
    private Boolean isRead;
    @TableField(value="mc.message_center_id")
    @JsonProperty(value = "messageCenterId")
    @Excel(name = "主键")
    private Integer messageCenterId;
    @TableField(value="mc.title")
    @JsonProperty(value = "title")
    @Excel(name = "标题")
    private String title;
    @TableField(value="mc.content")
    @JsonProperty(value = "content")
    @Excel(name = "内容")
    private String content;
    @TableField(value="mte.type_name")
    @JsonProperty(value = "typeName")
    @Excel(name = "类型名称")
    private String typeName;

}
