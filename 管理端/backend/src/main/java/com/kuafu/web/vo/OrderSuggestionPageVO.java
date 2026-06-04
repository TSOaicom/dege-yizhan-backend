package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class OrderSuggestionPageVO extends PageRequest {

    @JsonProperty(value = "orderSuggestionId")
    private Integer orderSuggestionId;
    @JsonProperty(value = "areaName")
    private String areaName;
    @JsonProperty(value = "orderCount")
    private Integer orderCount;
    @JsonProperty(value = "distance")
    private String distance;
    @JsonProperty(value = "recommendIndex")
    private String recommendIndex;
    @JsonProperty(value = "suggestDirection")
    private String suggestDirection;
    @JsonProperty(value = "heatLevelEnumHeatLevelEnumId1")
    private Integer heatLevelEnumHeatLevelEnumId1;

}
