package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

/**
 * <p>订单建议</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSuggestionVO  {

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
