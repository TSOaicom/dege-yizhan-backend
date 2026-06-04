package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.Feedback;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> feedback Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface FeedbackStatisticsMapper extends BaseMapper<Feedback> {

                        List<Object> feedback_statistic_88d8c4e8_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> feedback_statistic_567cc485_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> feedback_statistic_97294634_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> feedback_create_time_datetime_statistic_a0fae0b9_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> feedback_create_time_datetime_statistic_747f6f44_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> feedback_create_time_datetime_statistic_f2b17184_count(@Param("ew") Wrapper queryWrapper);

}
