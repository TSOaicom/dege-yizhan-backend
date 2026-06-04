package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.UserInfo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> user_info Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface UserInfoStatisticsMapper extends BaseMapper<UserInfo> {

                        List<Object> user_info_statistic_93e31c52_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_statistic_b65ed9bf_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_statistic_30868a86_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_statistic_268d8230_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_statistic_e7862c5d_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_statistic_152a46d0_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(@Param("ew") Wrapper queryWrapper);

}
