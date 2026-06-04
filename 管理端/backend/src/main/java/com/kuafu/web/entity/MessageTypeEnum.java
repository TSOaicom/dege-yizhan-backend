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
 * <p>  消息类型 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("message_type_enum")
public class MessageTypeEnum    {
    @TableId(value = "message_type_enum_id", type = IdType.AUTO)
    @JsonProperty(value = "messageTypeEnumId")
    @Excel(name = "主键")

    private Integer messageTypeEnumId;
    @JsonProperty(value = "typeName")
    @Excel(name = "类型名称")
    @ExcelProperty(value = "类型名称")
    @TableField(value = "type_name")

    private String typeName;


}
