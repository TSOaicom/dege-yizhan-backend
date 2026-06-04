package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.OrderSuggestion;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> order_suggestion Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface OrderSuggestionStatisticsMapper extends BaseMapper<OrderSuggestion> {

                        List<Object> order_suggestion_statistic_b2e54e19_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> order_suggestion_statistic_025d1cf0_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> order_suggestion_statistic_b0be95c4_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(@Param("ew") Wrapper queryWrapper);

}
