package com.kuafu.web.vo;

import com.kuafu.common.domin.PageRequest;
import com.kuafu.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.kuafu.common.entity.StaticResource;

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
public class OrderSuggestionAllPageVO extends BaseEntity {

@JsonProperty("areaName")
    private String areaName;
@JsonProperty("orderSuggestionId")
    private Integer orderSuggestionId;
@JsonProperty("levelName")
    private String levelName;
@JsonProperty("orderCount")
    private Integer orderCount;
@JsonProperty("distance")
    private String distance;
@JsonProperty("color")
    private String color;
@JsonProperty("heatLevelEnumHeatLevelEnumId1")
    private Integer heatLevelEnumHeatLevelEnumId1;
@JsonProperty("suggestDirection")
    private String suggestDirection;
@JsonProperty("recommendIndex")
    private String recommendIndex;

}
