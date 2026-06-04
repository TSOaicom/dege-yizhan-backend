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
import com.kuafu.web.entity.UserCertification;
import com.kuafu.web.service.IUserCertificationStatisticsService;
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
@RequestMapping("/userCertificationStatistics")

public class UserCertificationStatisticsController  {

    private final IUserCertificationStatisticsService userCertificationStatisticsService;


    @PostMapping("user_certification_statistic_7c28f5da_count")
    public BaseResponse user_certification_statistic_7c28f5da_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_7c28f5da_count(queryWrapper));
    }
    @PostMapping("user_certification_statistic_a5058d87_count")
    public BaseResponse user_certification_statistic_a5058d87_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_a5058d87_count(queryWrapper));
    }
    @PostMapping("user_certification_statistic_c2590f13_count")
    public BaseResponse user_certification_statistic_c2590f13_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_c2590f13_count(queryWrapper));
    }
    @PostMapping("user_certification_statistic_27b11a15_count")
    public BaseResponse user_certification_statistic_27b11a15_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_27b11a15_count(queryWrapper));
    }
    @PostMapping("user_certification_statistic_589059e4_count")
    public BaseResponse user_certification_statistic_589059e4_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_589059e4_count(queryWrapper));
    }
    @PostMapping("user_certification_statistic_40f91fb8_count")
    public BaseResponse user_certification_statistic_40f91fb8_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_statistic_40f91fb8_count(queryWrapper));
    }
    @PostMapping("user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count")
    public BaseResponse user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(queryWrapper));
    }
    @PostMapping("user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count")
    public BaseResponse user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(queryWrapper));
    }
    @PostMapping("user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count")
    public BaseResponse user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(@RequestBody UserCertification statisticVo) {
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userCertificationStatisticsService.user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<UserCertification> queryWrapper, UserCertification statisticVo){
            if(statisticVo.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, statisticVo.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(statisticVo.getDriverLicenseResourceKey())) {
                queryWrapper.eq(UserCertification::getDriverLicenseResourceKey, statisticVo.getDriverLicenseResourceKey());
            }
        if(StringUtils.isNotEmpty(statisticVo.getVehicleLicenseResourceKey())) {
                queryWrapper.eq(UserCertification::getVehicleLicenseResourceKey, statisticVo.getVehicleLicenseResourceKey());
            }
        if(StringUtils.isNotEmpty(statisticVo.getTaxiLicenseResourceKey())) {
                queryWrapper.eq(UserCertification::getTaxiLicenseResourceKey, statisticVo.getTaxiLicenseResourceKey());
            }
            if(statisticVo.getStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, statisticVo.getStatusEnumCertStatusEnumId1());
            }
        if(StringUtils.isNotEmpty(statisticVo.getRemark())) {
                queryWrapper.eq(UserCertification::getRemark, statisticVo.getRemark());
            }
    }



}
