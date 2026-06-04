package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.HeatLevelEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> heat_level_enum Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface HeatLevelEnumStatisticsMapper extends BaseMapper<HeatLevelEnum> {

                        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(@Param("ew") Wrapper queryWrapper);

}
