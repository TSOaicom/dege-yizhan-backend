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
import com.kuafu.web.entity.MemberTypeEnum;
import com.kuafu.web.service.IMemberTypeEnumStatisticsService;
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
@RequestMapping("/memberTypeEnumStatistics")

public class MemberTypeEnumStatisticsController  {

    private final IMemberTypeEnumStatisticsService memberTypeEnumStatisticsService;


    @PostMapping("member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count")
    public BaseResponse member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(@RequestBody MemberTypeEnum statisticVo) {
        LambdaQueryWrapper<MemberTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( memberTypeEnumStatisticsService.member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(queryWrapper));
    }
    @PostMapping("member_type_enum_member_type_enum_id_status_statistic_691c8685_count")
    public BaseResponse member_type_enum_member_type_enum_id_status_statistic_691c8685_count(@RequestBody MemberTypeEnum statisticVo) {
        LambdaQueryWrapper<MemberTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( memberTypeEnumStatisticsService.member_type_enum_member_type_enum_id_status_statistic_691c8685_count(queryWrapper));
    }
    @PostMapping("member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count")
    public BaseResponse member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(@RequestBody MemberTypeEnum statisticVo) {
        LambdaQueryWrapper<MemberTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( memberTypeEnumStatisticsService.member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<MemberTypeEnum> queryWrapper, MemberTypeEnum statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getTypeName())) {
                        queryWrapper.like(MemberTypeEnum::getTypeName, statisticVo.getTypeName());
            }
    }



}
