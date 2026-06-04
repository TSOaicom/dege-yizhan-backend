package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.InviteRecord;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> invite_record Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface InviteRecordStatisticsMapper extends BaseMapper<InviteRecord> {

                        List<Object> invite_record_statistic_31bed8f4_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> invite_record_statistic_a629efa5_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> invite_record_statistic_72eba71b_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> invite_record_invite_time_datetime_statistic_9e15a676_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> invite_record_invite_time_datetime_statistic_31df2134_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> invite_record_invite_time_datetime_statistic_8a084807_count(@Param("ew") Wrapper queryWrapper);

}
