package com.kuafu.web.vo;

import java.util.Date;

import com.kuafu.common.domin.PageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>认证状态-分页列表-响应参数</p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CertStatusEnumPageVO extends PageRequest {

    @JsonProperty(value = "certStatusEnumId")
    private Integer certStatusEnumId;
    @JsonProperty(value = "status")
    private String status;

}
