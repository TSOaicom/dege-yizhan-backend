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
public class MessageCenter    {
    @TableId(value = "message_center_id", type = IdType.AUTO)
    @JsonProperty(value = "messageCenterId")
    @Excel(name = "主键")

    private Integer messageCenterId;
    @JsonProperty(value = "title")
    @Excel(name = "标题")
    @ExcelProperty(value = "标题")
    @TableField(value = "title")

    private String title;
    @JsonProperty(value = "content")
    @Excel(name = "内容")
    @ExcelProperty(value = "内容")
    @TableField(value = "content")

    private String content;
    @JsonProperty(value = "typeEnumMessageTypeEnumId1")
    @Excel(name = "消息类型")
    @ExcelProperty(value = "消息类型")
    @TableField(value = "type_enum_message_type_enum_id_1")

    private Integer typeEnumMessageTypeEnumId1;
    @JsonProperty(value = "isRead")
    @Excel(name = "已读状态")
    @ExcelProperty(value = "已读状态")
    @TableField(value = "is_read")

    private Boolean isRead;
    @JsonProperty(value = "createTime")
    @Excel(dateFormat = "yyyy-MM-dd HH:mm:ss", name = "创建时间")
    @ExcelProperty(value = "创建时间")
    @TableField(value = "create_time")

    private Date createTime;


}
