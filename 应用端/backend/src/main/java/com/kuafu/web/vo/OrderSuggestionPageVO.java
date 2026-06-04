package com.kuafu.web.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kuafu.common.deserializer.MultiDateDeserializer;
import com.kuafu.common.domin.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.kuafu.common.entity.BaseEntity;
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
public class OrderSuggestionPageVO extends BaseEntity {

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
