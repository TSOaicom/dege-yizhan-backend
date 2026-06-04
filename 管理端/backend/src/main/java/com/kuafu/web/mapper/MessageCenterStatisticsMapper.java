package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.MessageCenter;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> message_center Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface MessageCenterStatisticsMapper extends BaseMapper<MessageCenter> {

                        List<Object> message_center_statistic_dfa049a3_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_statistic_475f0b61_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_statistic_f0ffe4fe_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_create_time_datetime_statistic_89be4e5d_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_create_time_datetime_statistic_4c1507ce_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_center_create_time_datetime_statistic_27bf0915_count(@Param("ew") Wrapper queryWrapper);

}
