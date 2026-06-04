package com.kuafu.web.controller;

import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.kuafu.common.resource.service.IStaticResourceService;
import com.kuafu.common.event.EventVo;
import com.kuafu.common.event.MyEventService;
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
import org.springframework.transaction.support.TransactionTemplate;
import com.kuafu.common.sensitive.SensitiveFilter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.OrderSuggestion;
import com.kuafu.web.service.IOrderSuggestionService;
import com.kuafu.web.vo.OrderSuggestionPageVO;
import com.kuafu.web.vo.OrderSuggestionVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 订单建议 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("OrderSuggestionControllerService")
@RequiredArgsConstructor
public class OrderSuggestionControllerService  {

    private final IOrderSuggestionService orderSuggestionService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( OrderSuggestionPageVO pageVO){
        IPage<OrderSuggestion> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<OrderSuggestion> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<OrderSuggestion> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(OrderSuggestion::getOrderSuggestionId);

            if(pageVO.getOrderSuggestionId() != null){
                queryWrapper.eq(OrderSuggestion::getOrderSuggestionId, pageVO.getOrderSuggestionId());
            }


        if(StringUtils.isNotEmpty(pageVO.getAreaName())) {
//                        queryWrapper.like(OrderSuggestion::getAreaName, pageVO.getAreaName());
                        String s_string = pageVO.getAreaName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(OrderSuggestion::getAreaName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }


            if(pageVO.getOrderCount() != null){
                queryWrapper.eq(OrderSuggestion::getOrderCount, pageVO.getOrderCount());
            }


        if(StringUtils.isNotEmpty(pageVO.getDistance())) {
                queryWrapper.eq(OrderSuggestion::getDistance, pageVO.getDistance());
            }


        if(StringUtils.isNotEmpty(pageVO.getRecommendIndex())) {
                queryWrapper.eq(OrderSuggestion::getRecommendIndex, pageVO.getRecommendIndex());
            }


        if(StringUtils.isNotEmpty(pageVO.getSuggestDirection())) {
                queryWrapper.eq(OrderSuggestion::getSuggestDirection, pageVO.getSuggestDirection());
            }


            if(pageVO.getHeatLevelEnumHeatLevelEnumId1() != null){
                queryWrapper.eq(OrderSuggestion::getHeatLevelEnumHeatLevelEnumId1, pageVO.getHeatLevelEnumHeatLevelEnumId1());
            }

            return ResultUtils.success(orderSuggestionService.page(page, queryWrapper));
        }


        public BaseResponse list(  OrderSuggestionVO vo){
            LambdaQueryWrapper<OrderSuggestion> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getAreaName())) {
                            queryWrapper.like(OrderSuggestion::getAreaName, vo.getAreaName());
                }
                if(vo.getOrderCount() != null){
                    queryWrapper.eq(OrderSuggestion::getOrderCount, vo.getOrderCount());
                }
            if(StringUtils.isNotEmpty(vo.getDistance())) {
                    queryWrapper.eq(OrderSuggestion::getDistance, vo.getDistance());
                }
            if(StringUtils.isNotEmpty(vo.getRecommendIndex())) {
                    queryWrapper.eq(OrderSuggestion::getRecommendIndex, vo.getRecommendIndex());
                }
            if(StringUtils.isNotEmpty(vo.getSuggestDirection())) {
                    queryWrapper.eq(OrderSuggestion::getSuggestDirection, vo.getSuggestDirection());
                }
                if(vo.getHeatLevelEnumHeatLevelEnumId1() != null){
                    queryWrapper.eq(OrderSuggestion::getHeatLevelEnumHeatLevelEnumId1, vo.getHeatLevelEnumHeatLevelEnumId1());
                }
                return ResultUtils.success(orderSuggestionService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  OrderSuggestionVO vo) {
                OrderSuggestion entity =  OrderSuggestion.builder()
                    .areaName(vo.getAreaName())
                    .orderCount(vo.getOrderCount())
                    .distance(vo.getDistance())
                    .recommendIndex(vo.getRecommendIndex())
                    .suggestDirection(vo.getSuggestDirection())
                    .heatLevelEnumHeatLevelEnumId1(vo.getHeatLevelEnumHeatLevelEnumId1())
                .build();








                boolean flag =this.orderSuggestionService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("order_suggestion").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getOrderSuggestionId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( OrderSuggestionVO vo) {
                OrderSuggestion entity =  OrderSuggestion.builder()
                    .orderSuggestionId(vo.getOrderSuggestionId())
                    .areaName(vo.getAreaName())
                    .orderCount(vo.getOrderCount())
                    .distance(vo.getDistance())
                    .recommendIndex(vo.getRecommendIndex())
                    .suggestDirection(vo.getSuggestDirection())
                    .heatLevelEnumHeatLevelEnumId1(vo.getHeatLevelEnumHeatLevelEnumId1())
                .build();



                        boolean flag = this.orderSuggestionService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getOrderSuggestionId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( OrderSuggestionVO vo ) {
                OrderSuggestion entity = this.orderSuggestionService.getById(vo.getOrderSuggestionId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( OrderSuggestionVO vo) {
                boolean flag = this.orderSuggestionService.removeById(vo.getOrderSuggestionId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
