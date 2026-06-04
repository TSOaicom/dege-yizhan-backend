package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * <p>消息中心-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageCenterAllPageVO extends PageRequest {

    @JsonProperty(value = "createTime")
    private Date createTime;
    @JsonProperty(value = "typeEnumMessageTypeEnumId1")
    private Integer typeEnumMessageTypeEnumId1;
    @JsonProperty(value = "isRead")
    private Boolean isRead;
    @JsonProperty(value = "messageCenterId")
    private Integer messageCenterId;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "content")
    private String content;
    @JsonProperty(value = "typeName")
    private String typeName;

}
