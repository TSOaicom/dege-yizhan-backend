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
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.IUserInfoStatisticsService;
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
@RequestMapping("/userInfoStatistics")

public class UserInfoStatisticsController  {

    private final IUserInfoStatisticsService userInfoStatisticsService;


    @PostMapping("user_info_statistic_93e31c52_count")
    public BaseResponse user_info_statistic_93e31c52_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_93e31c52_count(queryWrapper));
    }
    @PostMapping("user_info_statistic_b65ed9bf_count")
    public BaseResponse user_info_statistic_b65ed9bf_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_b65ed9bf_count(queryWrapper));
    }
    @PostMapping("user_info_statistic_30868a86_count")
    public BaseResponse user_info_statistic_30868a86_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_30868a86_count(queryWrapper));
    }
    @PostMapping("user_info_statistic_268d8230_count")
    public BaseResponse user_info_statistic_268d8230_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_268d8230_count(queryWrapper));
    }
    @PostMapping("user_info_statistic_e7862c5d_count")
    public BaseResponse user_info_statistic_e7862c5d_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_e7862c5d_count(queryWrapper));
    }
    @PostMapping("user_info_statistic_152a46d0_count")
    public BaseResponse user_info_statistic_152a46d0_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_statistic_152a46d0_count(queryWrapper));
    }
    @PostMapping("user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count")
    public BaseResponse user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(queryWrapper));
    }
    @PostMapping("user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count")
    public BaseResponse user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(queryWrapper));
    }
    @PostMapping("user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count")
    public BaseResponse user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(queryWrapper));
    }
    @PostMapping("user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count")
    public BaseResponse user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(queryWrapper));
    }
    @PostMapping("user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count")
    public BaseResponse user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(queryWrapper));
    }
    @PostMapping("user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count")
    public BaseResponse user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(@RequestBody UserInfo statisticVo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( userInfoStatisticsService.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<UserInfo> queryWrapper, UserInfo statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getPhoneNumber())) {
                queryWrapper.eq(UserInfo::getPhoneNumber, statisticVo.getPhoneNumber());
            }
        if(StringUtils.isNotEmpty(statisticVo.getCity())) {
                queryWrapper.eq(UserInfo::getCity, statisticVo.getCity());
            }
        if(StringUtils.isNotEmpty(statisticVo.getCarPlate())) {
                queryWrapper.eq(UserInfo::getCarPlate, statisticVo.getCarPlate());
            }
            if(statisticVo.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(UserInfo::getCarTypeEnumCarTypeEnumId1, statisticVo.getCarTypeEnumCarTypeEnumId1());
            }
        if(StringUtils.isNotEmpty(statisticVo.getAvatarResourceKey())) {
                queryWrapper.eq(UserInfo::getAvatarResourceKey, statisticVo.getAvatarResourceKey());
            }
        if(StringUtils.isNotEmpty(statisticVo.getRealName())) {
                        queryWrapper.like(UserInfo::getRealName, statisticVo.getRealName());
            }
            if(statisticVo.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserInfo::getCertStatusEnumCertStatusEnumId1, statisticVo.getCertStatusEnumCertStatusEnumId1());
            }
            if(statisticVo.getActiveDays() != null){
                queryWrapper.eq(UserInfo::getActiveDays, statisticVo.getActiveDays());
            }
            if(statisticVo.getContinueActiveDays() != null){
                queryWrapper.eq(UserInfo::getContinueActiveDays, statisticVo.getContinueActiveDays());
            }
        if(StringUtils.isNotEmpty(statisticVo.getPassword())) {
                queryWrapper.eq(UserInfo::getPassword, statisticVo.getPassword());
            }
    }



}
