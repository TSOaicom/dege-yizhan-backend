package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

/**
 * <p>厕所停车</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToiletParkingVO  {

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
