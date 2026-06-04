package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

/**
 * <p>消息中心</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCenterVO  {

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
