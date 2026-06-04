package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.CertStatusEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  cert_status_enum 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface ICertStatusEnumStatisticsService extends IService<CertStatusEnum> {
        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(LambdaQueryWrapper queryWrapper);

        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(LambdaQueryWrapper queryWrapper);

        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(LambdaQueryWrapper queryWrapper);

        List<Object> cert_status_enum_status_status_statistic_c41cd054_count(LambdaQueryWrapper queryWrapper);

        List<Object> cert_status_enum_status_status_statistic_9cd65863_count(LambdaQueryWrapper queryWrapper);

        List<Object> cert_status_enum_status_status_statistic_821aa606_count(LambdaQueryWrapper queryWrapper);

}
