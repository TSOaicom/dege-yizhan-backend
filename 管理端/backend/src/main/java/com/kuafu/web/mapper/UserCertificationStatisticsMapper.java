package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.UserCertification;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> user_certification Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface UserCertificationStatisticsMapper extends BaseMapper<UserCertification> {

                        List<Object> user_certification_statistic_7c28f5da_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_statistic_a5058d87_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_statistic_c2590f13_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_statistic_27b11a15_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_statistic_589059e4_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_statistic_40f91fb8_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(@Param("ew") Wrapper queryWrapper);

}
