package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.UserCertification;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  user_certification 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IUserCertificationStatisticsService extends IService<UserCertification> {
        List<Object> user_certification_statistic_7c28f5da_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_statistic_a5058d87_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_statistic_c2590f13_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_statistic_27b11a15_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_statistic_589059e4_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_statistic_40f91fb8_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(LambdaQueryWrapper queryWrapper);

}
