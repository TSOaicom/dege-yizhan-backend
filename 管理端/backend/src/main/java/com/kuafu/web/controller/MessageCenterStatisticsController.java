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
import com.kuafu.web.entity.MessageCenter;
import com.kuafu.web.service.IMessageCenterStatisticsService;
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
@RequestMapping("/messageCenterStatistics")

public class MessageCenterStatisticsController  {

    private final IMessageCenterStatisticsService messageCenterStatisticsService;


    @PostMapping("message_center_statistic_dfa049a3_count")
    public BaseResponse message_center_statistic_dfa049a3_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_statistic_dfa049a3_count(queryWrapper));
    }
    @PostMapping("message_center_statistic_475f0b61_count")
    public BaseResponse message_center_statistic_475f0b61_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_statistic_475f0b61_count(queryWrapper));
    }
    @PostMapping("message_center_statistic_f0ffe4fe_count")
    public BaseResponse message_center_statistic_f0ffe4fe_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_statistic_f0ffe4fe_count(queryWrapper));
    }
    @PostMapping("message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count")
    public BaseResponse message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(queryWrapper));
    }
    @PostMapping("message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count")
    public BaseResponse message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(queryWrapper));
    }
    @PostMapping("message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count")
    public BaseResponse message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(queryWrapper));
    }
    @PostMapping("message_center_create_time_datetime_statistic_89be4e5d_count")
    public BaseResponse message_center_create_time_datetime_statistic_89be4e5d_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_create_time_datetime_statistic_89be4e5d_count(queryWrapper));
    }
    @PostMapping("message_center_create_time_datetime_statistic_4c1507ce_count")
    public BaseResponse message_center_create_time_datetime_statistic_4c1507ce_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_create_time_datetime_statistic_4c1507ce_count(queryWrapper));
    }
    @PostMapping("message_center_create_time_datetime_statistic_27bf0915_count")
    public BaseResponse message_center_create_time_datetime_statistic_27bf0915_count(@RequestBody MessageCenter statisticVo) {
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageCenterStatisticsService.message_center_create_time_datetime_statistic_27bf0915_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<MessageCenter> queryWrapper, MessageCenter statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getTitle())) {
                queryWrapper.eq(MessageCenter::getTitle, statisticVo.getTitle());
            }
        if(StringUtils.isNotEmpty(statisticVo.getContent())) {
                queryWrapper.eq(MessageCenter::getContent, statisticVo.getContent());
            }
            if(statisticVo.getTypeEnumMessageTypeEnumId1() != null){
                queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, statisticVo.getTypeEnumMessageTypeEnumId1());
            }
            if(statisticVo.getIsRead() != null){
                queryWrapper.eq(MessageCenter::getIsRead, statisticVo.getIsRead());
            }
            if(statisticVo.getCreateTime() != null){
                queryWrapper.eq(MessageCenter::getCreateTime, statisticVo.getCreateTime());
            }
    }



}
