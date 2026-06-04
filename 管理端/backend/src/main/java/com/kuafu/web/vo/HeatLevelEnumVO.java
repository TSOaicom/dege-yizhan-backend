package com.kuafu.web.vo;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import com.kuafu.common.domin.StaticResource;
import java.util.List;

/**
 * <p>热力等级</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatLevelEnumVO  {

     @JsonProperty(value = "heatLevelEnumId")
    private Integer heatLevelEnumId;
     @JsonProperty(value = "levelName")
    private String levelName;
     @JsonProperty(value = "color")
    private String color;


}
