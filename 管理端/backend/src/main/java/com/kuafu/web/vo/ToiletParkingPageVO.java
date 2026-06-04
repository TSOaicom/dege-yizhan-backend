package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>厕所停车-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ToiletParkingPageVO extends PageRequest {

    @JsonProperty(value = "toiletParkingId")
    private Integer toiletParkingId;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "latitude")
    private String latitude;
    @JsonProperty(value = "longitude")
    private String longitude;
    @JsonProperty(value = "hasParking")
    private Boolean hasParking;
    @JsonProperty(value = "parkingCount")
    private Integer parkingCount;
    @JsonProperty(value = "isOpen")
    private Boolean isOpen;

}
