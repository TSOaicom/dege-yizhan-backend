package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class MessageCenterPageVO extends PageRequest {

    @JsonProperty(value = "messageCenterId")
    private Integer messageCenterId;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "content")
    private String content;
    @JsonProperty(value = "typeEnumMessageTypeEnumId1")
    private Integer typeEnumMessageTypeEnumId1;
    @JsonProperty(value = "isRead")
    private Boolean isRead;
    @JsonProperty(value = "createTime")
    private Date createTime;

}
