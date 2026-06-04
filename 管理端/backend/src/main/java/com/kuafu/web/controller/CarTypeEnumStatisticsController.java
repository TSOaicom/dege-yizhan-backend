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
import com.kuafu.web.entity.CarTypeEnum;
import com.kuafu.web.service.ICarTypeEnumStatisticsService;
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
@RequestMapping("/carTypeEnumStatistics")

public class CarTypeEnumStatisticsController  {

    private final ICarTypeEnumStatisticsService carTypeEnumStatisticsService;


    @PostMapping("car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count")
    public BaseResponse car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(@RequestBody CarTypeEnum statisticVo) {
        LambdaQueryWrapper<CarTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( carTypeEnumStatisticsService.car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(queryWrapper));
    }
    @PostMapping("car_type_enum_car_type_enum_id_status_statistic_54e8df16_count")
    public BaseResponse car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(@RequestBody CarTypeEnum statisticVo) {
        LambdaQueryWrapper<CarTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( carTypeEnumStatisticsService.car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(queryWrapper));
    }
    @PostMapping("car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count")
    public BaseResponse car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(@RequestBody CarTypeEnum statisticVo) {
        LambdaQueryWrapper<CarTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( carTypeEnumStatisticsService.car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<CarTypeEnum> queryWrapper, CarTypeEnum statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getTypeName())) {
                        queryWrapper.like(CarTypeEnum::getTypeName, statisticVo.getTypeName());
            }
    }



}
