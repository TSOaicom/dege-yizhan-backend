package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.Feedback;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  feedback 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IFeedbackStatisticsService extends IService<Feedback> {
        List<Object> feedback_statistic_88d8c4e8_count(LambdaQueryWrapper queryWrapper);

        List<Object> feedback_statistic_567cc485_count(LambdaQueryWrapper queryWrapper);

        List<Object> feedback_statistic_97294634_count(LambdaQueryWrapper queryWrapper);

        List<Object> feedback_create_time_datetime_statistic_a0fae0b9_count(LambdaQueryWrapper queryWrapper);

        List<Object> feedback_create_time_datetime_statistic_747f6f44_count(LambdaQueryWrapper queryWrapper);

        List<Object> feedback_create_time_datetime_statistic_f2b17184_count(LambdaQueryWrapper queryWrapper);

}
