package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.CarTypeEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> car_type_enum Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface CarTypeEnumStatisticsMapper extends BaseMapper<CarTypeEnum> {

                        List<Object> car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(@Param("ew") Wrapper queryWrapper);

}
