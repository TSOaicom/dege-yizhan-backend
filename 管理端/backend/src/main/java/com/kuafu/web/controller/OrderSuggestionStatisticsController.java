package com.kuafu.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.OrderSuggestion;
import com.kuafu.web.service.IOrderSuggestionStatisticsService;
import com.kuafu.web.entity.SelectVO;

import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;





/**

 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderSuggestionStatistics")

public class OrderSuggestionStatisticsController  {

    private final IOrderSuggestionStatisticsService orderSuggestionStatisticsService;


    @PostMapping("order_suggestion_statistic_b2e54e19_count")
    public BaseResponse order_suggestion_statistic_b2e54e19_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_statistic_b2e54e19_count(queryWrapper));
    }
    @PostMapping("order_suggestion_statistic_025d1cf0_count")
    public BaseResponse order_suggestion_statistic_025d1cf0_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_statistic_025d1cf0_count(queryWrapper));
    }
    @PostMapping("order_suggestion_statistic_b0be95c4_count")
    public BaseResponse order_suggestion_statistic_b0be95c4_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_statistic_b0be95c4_count(queryWrapper));
    }
    @PostMapping("order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count")
    public BaseResponse order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_fa3c8c86_count(queryWrapper));
    }
    @PostMapping("order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count")
    public BaseResponse order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_f66eba9a_count(queryWrapper));
    }
    @PostMapping("order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count")
    public BaseResponse order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(@RequestBody OrderSuggestion statisticVo) {
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( orderSuggestionStatisticsService.order_suggestion_heat_level_enum_heat_level_enum_id_1_status_statistic_e974660b_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<OrderSuggestion> queryWrapper, OrderSuggestion statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getAreaName())) {
                        queryWrapper.like(OrderSuggestion::getAreaName, statisticVo.getAreaName());
            }
            if(statisticVo.getOrderCount() != null){
                queryWrapper.eq(OrderSuggestion::getOrderCount, statisticVo.getOrderCount());
            }
        if(StringUtils.isNotEmpty(statisticVo.getDistance())) {
                queryWrapper.eq(OrderSuggestion::getDistance, statisticVo.getDistance());
            }
        if(StringUtils.isNotEmpty(statisticVo.getRecommendIndex())) {
                queryWrapper.eq(OrderSuggestion::getRecommendIndex, statisticVo.getRecommendIndex());
            }
        if(StringUtils.isNotEmpty(statisticVo.getSuggestDirection())) {
                queryWrapper.eq(OrderSuggestion::getSuggestDirection, statisticVo.getSuggestDirection());
            }
            if(statisticVo.getHeatLevelEnumHeatLevelEnumId1() != null){
                queryWrapper.eq(OrderSuggestion::getHeatLevelEnumHeatLevelEnumId1, statisticVo.getHeatLevelEnumHeatLevelEnumId1());
            }
    }



}
