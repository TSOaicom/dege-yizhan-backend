package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * <p>订单建议-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderSuggestionAllPageVO extends PageRequest {

    @JsonProperty(value = "suggestDirection")
    private String suggestDirection;
    @JsonProperty(value = "areaName")
    private String areaName;
    @JsonProperty(value = "distance")
    private String distance;
    @JsonProperty(value = "color")
    private String color;
    @JsonProperty(value = "heatLevelEnumHeatLevelEnumId1")
    private Integer heatLevelEnumHeatLevelEnumId1;
    @JsonProperty(value = "recommendIndex")
    private String recommendIndex;
    @JsonProperty(value = "orderSuggestionId")
    private Integer orderSuggestionId;
    @JsonProperty(value = "levelName")
    private String levelName;
    @JsonProperty(value = "orderCount")
    private Integer orderCount;

}
