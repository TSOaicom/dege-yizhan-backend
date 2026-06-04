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
import com.kuafu.web.entity.Feedback;
import com.kuafu.web.service.IFeedbackStatisticsService;
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
@RequestMapping("/feedbackStatistics")

public class FeedbackStatisticsController  {

    private final IFeedbackStatisticsService feedbackStatisticsService;


    @PostMapping("feedback_statistic_88d8c4e8_count")
    public BaseResponse feedback_statistic_88d8c4e8_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_statistic_88d8c4e8_count(queryWrapper));
    }
    @PostMapping("feedback_statistic_567cc485_count")
    public BaseResponse feedback_statistic_567cc485_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_statistic_567cc485_count(queryWrapper));
    }
    @PostMapping("feedback_statistic_97294634_count")
    public BaseResponse feedback_statistic_97294634_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_statistic_97294634_count(queryWrapper));
    }
    @PostMapping("feedback_create_time_datetime_statistic_a0fae0b9_count")
    public BaseResponse feedback_create_time_datetime_statistic_a0fae0b9_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_create_time_datetime_statistic_a0fae0b9_count(queryWrapper));
    }
    @PostMapping("feedback_create_time_datetime_statistic_747f6f44_count")
    public BaseResponse feedback_create_time_datetime_statistic_747f6f44_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_create_time_datetime_statistic_747f6f44_count(queryWrapper));
    }
    @PostMapping("feedback_create_time_datetime_statistic_f2b17184_count")
    public BaseResponse feedback_create_time_datetime_statistic_f2b17184_count(@RequestBody Feedback statisticVo) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( feedbackStatisticsService.feedback_create_time_datetime_statistic_f2b17184_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<Feedback> queryWrapper, Feedback statisticVo){
            if(statisticVo.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(Feedback::getUserInfoUserInfoId1, statisticVo.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(statisticVo.getContent())) {
                queryWrapper.eq(Feedback::getContent, statisticVo.getContent());
            }
            if(statisticVo.getCreateTime() != null){
                queryWrapper.eq(Feedback::getCreateTime, statisticVo.getCreateTime());
            }
    }



}
