package com.kuafu.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import java.io.Serializable;
import com.kuafu.common.login.IControllerService;

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
import org.apache.commons.lang3.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kuafu.login.annotation.*;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.OrderSuggestionAll;
import com.kuafu.web.service.IOrderSuggestionAllService;
import com.kuafu.web.vo.OrderSuggestionAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IHeatLevelEnumService;
import com.kuafu.web.entity.HeatLevelEnum;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 订单建议 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("OrderSuggestionAllControllerService")
public class OrderSuggestionAllControllerService implements IControllerService<OrderSuggestionAll> {


@Autowired
    private  IOrderSuggestionAllService orderSuggestionAllService;








    public BaseResponse page( OrderSuggestionAllPageVO pageVO){
        IPage<OrderSuggestionAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<OrderSuggestionAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("os.order_suggestion_id");
        LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = objectQueryWrapper.lambda();


        if(StringUtils.isNotEmpty(pageVO.getAreaName())) {

                        String s_string = pageVO.getAreaName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(OrderSuggestionAll::getAreaName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getOrderSuggestionId() != null){
                queryWrapper.eq(OrderSuggestionAll::getOrderSuggestionId, pageVO.getOrderSuggestionId());
            }
        if(StringUtils.isNotEmpty(pageVO.getLevelName())) {

                        String s_string = pageVO.getLevelName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(OrderSuggestionAll::getLevelName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getOrderCount() != null){
                queryWrapper.eq(OrderSuggestionAll::getOrderCount, pageVO.getOrderCount());
            }
        if(StringUtils.isNotEmpty(pageVO.getDistance())) {
                queryWrapper.like(OrderSuggestionAll::getDistance, pageVO.getDistance());
            }
        if(StringUtils.isNotEmpty(pageVO.getColor())) {
                queryWrapper.like(OrderSuggestionAll::getColor, pageVO.getColor());
            }
            if(pageVO.getHeatLevelEnumHeatLevelEnumId1() != null){
                queryWrapper.eq(OrderSuggestionAll::getHeatLevelEnumHeatLevelEnumId1, pageVO.getHeatLevelEnumHeatLevelEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getSuggestDirection())) {
                queryWrapper.like(OrderSuggestionAll::getSuggestDirection, pageVO.getSuggestDirection());
            }
        if(StringUtils.isNotEmpty(pageVO.getRecommendIndex())) {
                queryWrapper.like(OrderSuggestionAll::getRecommendIndex, pageVO.getRecommendIndex());
            }

                    orderSuggestionAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(OrderSuggestionAllPageVO vo){
            LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getAreaName())) {
                            queryWrapper.like(OrderSuggestionAll::getAreaName, vo.getAreaName());
                }
            if(StringUtils.isNotEmpty(vo.getLevelName())) {
                            queryWrapper.like(OrderSuggestionAll::getLevelName, vo.getLevelName());
                }
                if(vo.getOrderCount() != null){
                    queryWrapper.eq(OrderSuggestionAll::getOrderCount, vo.getOrderCount());
                }
            if(StringUtils.isNotEmpty(vo.getDistance())) {
                    queryWrapper.eq(OrderSuggestionAll::getDistance, vo.getDistance());
                }
            if(StringUtils.isNotEmpty(vo.getColor())) {
                    queryWrapper.eq(OrderSuggestionAll::getColor, vo.getColor());
                }
                if(vo.getHeatLevelEnumHeatLevelEnumId1() != null){
                    queryWrapper.eq(OrderSuggestionAll::getHeatLevelEnumHeatLevelEnumId1, vo.getHeatLevelEnumHeatLevelEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getSuggestDirection())) {
                    queryWrapper.eq(OrderSuggestionAll::getSuggestDirection, vo.getSuggestDirection());
                }
            if(StringUtils.isNotEmpty(vo.getRecommendIndex())) {
                    queryWrapper.eq(OrderSuggestionAll::getRecommendIndex, vo.getRecommendIndex());
                }
                return ResultUtils.success(orderSuggestionAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(OrderSuggestionAllPageVO vo) {


                LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();


                            if(StringUtils.isNotEmpty(vo.getAreaName())) {
                                            queryWrapper.like(OrderSuggestionAll::getAreaName, vo.getAreaName());
                                }


                                if(vo.getOrderSuggestionId() != null){
                                    queryWrapper.eq(OrderSuggestionAll::getOrderSuggestionId, vo.getOrderSuggestionId());
                                }


                            if(StringUtils.isNotEmpty(vo.getLevelName())) {
                                            queryWrapper.like(OrderSuggestionAll::getLevelName, vo.getLevelName());
                                }


                                if(vo.getOrderCount() != null){
                                    queryWrapper.eq(OrderSuggestionAll::getOrderCount, vo.getOrderCount());
                                }


                            if(StringUtils.isNotEmpty(vo.getDistance())) {
                                    queryWrapper.eq(OrderSuggestionAll::getDistance, vo.getDistance());
                                }


                            if(StringUtils.isNotEmpty(vo.getColor())) {
                                    queryWrapper.eq(OrderSuggestionAll::getColor, vo.getColor());
                                }


                                if(vo.getHeatLevelEnumHeatLevelEnumId1() != null){
                                    queryWrapper.eq(OrderSuggestionAll::getHeatLevelEnumHeatLevelEnumId1, vo.getHeatLevelEnumHeatLevelEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getSuggestDirection())) {
                                    queryWrapper.eq(OrderSuggestionAll::getSuggestDirection, vo.getSuggestDirection());
                                }


                            if(StringUtils.isNotEmpty(vo.getRecommendIndex())) {
                                    queryWrapper.eq(OrderSuggestionAll::getRecommendIndex, vo.getRecommendIndex());
                                }

                // OrderSuggestionAll entity = this.orderSuggestionAllService.getById(orderSuggestionId);
                    final Page<OrderSuggestionAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    orderSuggestionAllService.pageNew(page, vo, queryWrapper);
                    final List<OrderSuggestionAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public OrderSuggestionAll getById(Serializable orderSuggestionId) {

                    LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(OrderSuggestionAll::getOrderSuggestionId, orderSuggestionId);

                    final Page<OrderSuggestionAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    orderSuggestionAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<OrderSuggestionAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
