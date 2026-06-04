package com.kuafu.web.service.impl;

import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kuafu.web.mapper.OrderSuggestionStatisticsMapper;
import com.kuafu.web.entity.OrderSuggestion;
import com.kuafu.web.service.IOrderSuggestionStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> order_suggestion 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("OrderSuggestionStatistics")
public class OrderSuggestionStatisticsServiceImpl extends ServiceImpl<OrderSuggestionStatisticsMapper, OrderSuggestion> implements IOrderSuggestionStatisticsService {
    @Resource
    private OrderSuggestionStatisticsMapper orderSuggestionStaticMapper;

                        @Override
                        public List<Object> order_suggestion_statistic_b2e54e19_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_statistic_b2e54e19_count(queryWrapper);
                                }


                        @Override
                        public List<Object> order_suggestion_statistic_025d1cf0_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_statistic_025d1cf0_count(queryWrapper);
                                }


                        @Override
                        public List<Object> order_suggestion_statistic_b0be95c4_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_statistic_b0be95c4_count(queryWrapper);
                                }


                        @Override
                        public List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(queryWrapper);
                                }


                        @Override
                        public List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(queryWrapper);
                                }


                        @Override
                        public List<Object> order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(LambdaQueryWrapper queryWrapper) {
                                return orderSuggestionStaticMapper.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(queryWrapper);
                                }


}
