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
import com.kuafu.web.entity.InviteRecord;
import com.kuafu.web.service.IInviteRecordStatisticsService;
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
@RequestMapping("/inviteRecordStatistics")

public class InviteRecordStatisticsController  {

    private final IInviteRecordStatisticsService inviteRecordStatisticsService;


    @PostMapping("invite_record_statistic_31bed8f4_count")
    public BaseResponse invite_record_statistic_31bed8f4_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_statistic_31bed8f4_count(queryWrapper));
    }
    @PostMapping("invite_record_statistic_a629efa5_count")
    public BaseResponse invite_record_statistic_a629efa5_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_statistic_a629efa5_count(queryWrapper));
    }
    @PostMapping("invite_record_statistic_72eba71b_count")
    public BaseResponse invite_record_statistic_72eba71b_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_statistic_72eba71b_count(queryWrapper));
    }
    @PostMapping("invite_record_invite_time_datetime_statistic_9e15a676_count")
    public BaseResponse invite_record_invite_time_datetime_statistic_9e15a676_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_invite_time_datetime_statistic_9e15a676_count(queryWrapper));
    }
    @PostMapping("invite_record_invite_time_datetime_statistic_31df2134_count")
    public BaseResponse invite_record_invite_time_datetime_statistic_31df2134_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_invite_time_datetime_statistic_31df2134_count(queryWrapper));
    }
    @PostMapping("invite_record_invite_time_datetime_statistic_8a084807_count")
    public BaseResponse invite_record_invite_time_datetime_statistic_8a084807_count(@RequestBody InviteRecord statisticVo) {
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( inviteRecordStatisticsService.invite_record_invite_time_datetime_statistic_8a084807_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<InviteRecord> queryWrapper, InviteRecord statisticVo){
            if(statisticVo.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, statisticVo.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(statisticVo.getInviteePhone())) {
                queryWrapper.eq(InviteRecord::getInviteePhone, statisticVo.getInviteePhone());
            }
            if(statisticVo.getInviteTime() != null){
                queryWrapper.eq(InviteRecord::getInviteTime, statisticVo.getInviteTime());
            }
    }



}
