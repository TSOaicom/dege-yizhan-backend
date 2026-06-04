package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.OrderSuggestion;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  order_suggestion 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IOrderSuggestionStatisticsService extends IService<OrderSuggestion> {
        List<Object> order_suggestion_statistic_b2e54e19_count(LambdaQueryWrapper queryWrapper);

        List<Object> order_suggestion_statistic_025d1cf0_count(LambdaQueryWrapper queryWrapper);

        List<Object> order_suggestion_statistic_b0be95c4_count(LambdaQueryWrapper queryWrapper);

        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(LambdaQueryWrapper queryWrapper);

        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(LambdaQueryWrapper queryWrapper);

        List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(LambdaQueryWrapper queryWrapper);

}
