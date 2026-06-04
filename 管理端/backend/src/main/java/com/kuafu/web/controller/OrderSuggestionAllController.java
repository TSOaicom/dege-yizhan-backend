package com.kuafu.web.controller;

import com.kuafu.common.util.poi.ExcelUtil;
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
import javax.servlet.http.HttpServletResponse;


import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.SelectVO;
import com.kuafu.web.entity.OrderSuggestionAll;
import com.kuafu.web.service.IOrderSuggestionAllService;
import com.kuafu.web.vo.OrderSuggestionAllPageVO;

import com.kuafu.web.service.IHeatLevelEnumService;
import com.kuafu.web.entity.HeatLevelEnum;


/**
 * <p> 订单建议 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderSuggestionAll")
@Api(tags = {"订单建议"})
public class OrderSuggestionAllController  {

    private final IOrderSuggestionAllService orderSuggestionAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody OrderSuggestionAllPageVO pageVO){
        IPage<OrderSuggestionAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotEmpty(pageVO.getSuggestDirection())) {
            queryWrapper.eq(OrderSuggestionAll::getSuggestDirection, pageVO.getSuggestDirection());
        }
        if(StringUtils.isNotEmpty(pageVO.getAreaName())) {
            queryWrapper.like(OrderSuggestionAll::getAreaName, pageVO.getAreaName());
        }
        if(StringUtils.isNotEmpty(pageVO.getDistance())) {
            queryWrapper.eq(OrderSuggestionAll::getDistance, pageVO.getDistance());
        }
        if(StringUtils.isNotEmpty(pageVO.getColor())) {
            queryWrapper.eq(OrderSuggestionAll::getColor, pageVO.getColor());
        }
            if(pageVO.getHeatLevelEnumHeatLevelEnumId1() != null){
            queryWrapper.eq(OrderSuggestionAll::getHeatLevelEnumHeatLevelEnumId1, pageVO.getHeatLevelEnumHeatLevelEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getRecommendIndex())) {
            queryWrapper.eq(OrderSuggestionAll::getRecommendIndex, pageVO.getRecommendIndex());
        }
        if(StringUtils.isNotEmpty(pageVO.getLevelName())) {
            queryWrapper.like(OrderSuggestionAll::getLevelName, pageVO.getLevelName());
        }
            if(pageVO.getOrderCount() != null){
            queryWrapper.eq(OrderSuggestionAll::getOrderCount, pageVO.getOrderCount());
        }
        return ResultUtils.success(orderSuggestionAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody OrderSuggestionAllPageVO vo){
        LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getSuggestDirection())) {
            queryWrapper.eq(OrderSuggestionAll::getSuggestDirection, vo.getSuggestDirection());
        }
            if(StringUtils.isNotEmpty(vo.getAreaName())) {
            queryWrapper.like(OrderSuggestionAll::getAreaName, vo.getAreaName());
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
            if(StringUtils.isNotEmpty(vo.getRecommendIndex())) {
            queryWrapper.eq(OrderSuggestionAll::getRecommendIndex, vo.getRecommendIndex());
        }
            if(StringUtils.isNotEmpty(vo.getLevelName())) {
            queryWrapper.like(OrderSuggestionAll::getLevelName, vo.getLevelName());
        }
                if(vo.getOrderCount() != null){
            queryWrapper.eq(OrderSuggestionAll::getOrderCount, vo.getOrderCount());
        }
        return ResultUtils.success(orderSuggestionAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer orderSuggestionId) {
        OrderSuggestionAll entity = this.orderSuggestionAllService.getById(orderSuggestionId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


        private final IHeatLevelEnumService heatLevelEnumService;

        @GetMapping("get/heat_level_enum_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_heat_level_enum_list() {
            List<HeatLevelEnum> list = this.heatLevelEnumService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getHeatLevelEnumId(), p.getLevelName().toString()))
                        .collect(Collectors.toList());
            return ResultUtils.success(selectVoList);
        }


        /**
         * 导出excel
         */
        @PostMapping("/export")
        public void export(HttpServletResponse response, @RequestBody OrderSuggestionAllPageVO vo) {

            LambdaQueryWrapper<OrderSuggestionAll> queryWrapper = new LambdaQueryWrapper<>();

                if(StringUtils.isNotEmpty(vo.getSuggestDirection())) {
                        queryWrapper.eq(OrderSuggestionAll::getSuggestDirection, vo.getSuggestDirection());
                    }
                if(StringUtils.isNotEmpty(vo.getAreaName())) {
                                queryWrapper.like(OrderSuggestionAll::getAreaName, vo.getAreaName());
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
                if(StringUtils.isNotEmpty(vo.getRecommendIndex())) {
                        queryWrapper.eq(OrderSuggestionAll::getRecommendIndex, vo.getRecommendIndex());
                    }
                if(StringUtils.isNotEmpty(vo.getLevelName())) {
                                queryWrapper.like(OrderSuggestionAll::getLevelName, vo.getLevelName());
                    }
                    if(vo.getOrderCount() != null){
                        queryWrapper.eq(OrderSuggestionAll::getOrderCount, vo.getOrderCount());
                    }
            List<OrderSuggestionAll> list = orderSuggestionAllService.list(queryWrapper);
            ExcelUtil<OrderSuggestionAll> util = new ExcelUtil<>(OrderSuggestionAll.class);
            util.exportExcel(response, list, "数据");
        }


}
