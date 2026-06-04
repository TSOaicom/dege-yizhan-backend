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
 * <p>  会员类型 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("member_type_enum")
public class MemberTypeEnum    {
    @TableId(value = "member_type_enum_id", type = IdType.AUTO)
    @JsonProperty(value = "memberTypeEnumId")
    @Excel(name = "主键")

    private Integer memberTypeEnumId;
    @JsonProperty(value = "typeName")
    @Excel(name = "类型名称")
    @ExcelProperty(value = "类型名称")
    @TableField(value = "type_name")

    private String typeName;


}
