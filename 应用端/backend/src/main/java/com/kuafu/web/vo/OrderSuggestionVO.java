package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.kuafu.common.entity.StaticResource;
import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import com.kuafu.common.entity.BaseEntity;
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
public class OrderSuggestionVO  extends BaseEntity {

    @JsonProperty("orderSuggestionId")
    private Integer orderSuggestionId;
    @JsonProperty("areaName")
    private String areaName;
    @JsonProperty("orderCount")
    private Integer orderCount;
    @JsonProperty("distance")
    private String distance;
    @JsonProperty("recommendIndex")
    private String recommendIndex;
    @JsonProperty("suggestDirection")
    private String suggestDirection;
    @JsonProperty("heatLevelEnumHeatLevelEnumId1")
    private Integer heatLevelEnumHeatLevelEnumId1;




}
