package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  user_info 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IUserInfoStatisticsService extends IService<UserInfo> {
        List<Object> user_info_statistic_93e31c52_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_statistic_b65ed9bf_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_statistic_30868a86_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_statistic_268d8230_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_statistic_e7862c5d_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_statistic_152a46d0_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(LambdaQueryWrapper queryWrapper);

        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(LambdaQueryWrapper queryWrapper);

}
