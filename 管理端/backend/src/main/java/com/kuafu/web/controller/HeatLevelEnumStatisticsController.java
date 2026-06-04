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
import com.kuafu.web.entity.HeatLevelEnum;
import com.kuafu.web.service.IHeatLevelEnumStatisticsService;
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
@RequestMapping("/heatLevelEnumStatistics")

public class HeatLevelEnumStatisticsController  {

    private final IHeatLevelEnumStatisticsService heatLevelEnumStatisticsService;


    @PostMapping("heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count")
    public BaseResponse heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(@RequestBody HeatLevelEnum statisticVo) {
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( heatLevelEnumStatisticsService.heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(queryWrapper));
    }
    @PostMapping("heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count")
    public BaseResponse heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(@RequestBody HeatLevelEnum statisticVo) {
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( heatLevelEnumStatisticsService.heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(queryWrapper));
    }
    @PostMapping("heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count")
    public BaseResponse heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(@RequestBody HeatLevelEnum statisticVo) {
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( heatLevelEnumStatisticsService.heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<HeatLevelEnum> queryWrapper, HeatLevelEnum statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getLevelName())) {
                        queryWrapper.like(HeatLevelEnum::getLevelName, statisticVo.getLevelName());
            }
        if(StringUtils.isNotEmpty(statisticVo.getColor())) {
                queryWrapper.eq(HeatLevelEnum::getColor, statisticVo.getColor());
            }
    }



}
