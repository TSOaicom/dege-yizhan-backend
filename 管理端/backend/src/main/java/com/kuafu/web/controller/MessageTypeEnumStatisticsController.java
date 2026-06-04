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
import com.kuafu.web.entity.MessageTypeEnum;
import com.kuafu.web.service.IMessageTypeEnumStatisticsService;
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
@RequestMapping("/messageTypeEnumStatistics")

public class MessageTypeEnumStatisticsController  {

    private final IMessageTypeEnumStatisticsService messageTypeEnumStatisticsService;


    @PostMapping("message_type_enum_message_type_enum_id_status_statistic_48318e25_count")
    public BaseResponse message_type_enum_message_type_enum_id_status_statistic_48318e25_count(@RequestBody MessageTypeEnum statisticVo) {
        LambdaQueryWrapper<MessageTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageTypeEnumStatisticsService.message_type_enum_message_type_enum_id_status_statistic_48318e25_count(queryWrapper));
    }
    @PostMapping("message_type_enum_message_type_enum_id_status_statistic_0a19315b_count")
    public BaseResponse message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(@RequestBody MessageTypeEnum statisticVo) {
        LambdaQueryWrapper<MessageTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageTypeEnumStatisticsService.message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(queryWrapper));
    }
    @PostMapping("message_type_enum_message_type_enum_id_status_statistic_9669aff3_count")
    public BaseResponse message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(@RequestBody MessageTypeEnum statisticVo) {
        LambdaQueryWrapper<MessageTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( messageTypeEnumStatisticsService.message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<MessageTypeEnum> queryWrapper, MessageTypeEnum statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getTypeName())) {
                        queryWrapper.like(MessageTypeEnum::getTypeName, statisticVo.getTypeName());
            }
    }



}
