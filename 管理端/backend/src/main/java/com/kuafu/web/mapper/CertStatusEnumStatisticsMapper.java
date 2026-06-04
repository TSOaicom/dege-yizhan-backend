package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.CertStatusEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> cert_status_enum Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface CertStatusEnumStatisticsMapper extends BaseMapper<CertStatusEnum> {

                        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> cert_status_enum_status_status_statistic_c41cd054_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> cert_status_enum_status_status_statistic_9cd65863_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> cert_status_enum_status_status_statistic_821aa606_count(@Param("ew") Wrapper queryWrapper);

}
