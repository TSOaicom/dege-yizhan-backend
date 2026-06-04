package com.kuafu.web.service.impl;

import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kuafu.web.mapper.FeedbackStatisticsMapper;
import com.kuafu.web.entity.Feedback;
import com.kuafu.web.service.IFeedbackStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> feedback 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("FeedbackStatistics")
public class FeedbackStatisticsServiceImpl extends ServiceImpl<FeedbackStatisticsMapper, Feedback> implements IFeedbackStatisticsService {
    @Resource
    private FeedbackStatisticsMapper feedbackStaticMapper;

                        @Override
                        public List<Object> feedback_statistic_88d8c4e8_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_statistic_88d8c4e8_count(queryWrapper);
                                }


                        @Override
                        public List<Object> feedback_statistic_567cc485_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_statistic_567cc485_count(queryWrapper);
                                }


                        @Override
                        public List<Object> feedback_statistic_97294634_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_statistic_97294634_count(queryWrapper);
                                }


                        @Override
                        public List<Object> feedback_create_time_datetime_statistic_a0fae0b9_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_create_time_datetime_statistic_a0fae0b9_count(queryWrapper);
                                }


                        @Override
                        public List<Object> feedback_create_time_datetime_statistic_747f6f44_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_create_time_datetime_statistic_747f6f44_count(queryWrapper);
                                }


                        @Override
                        public List<Object> feedback_create_time_datetime_statistic_f2b17184_count(LambdaQueryWrapper queryWrapper) {
                                return feedbackStaticMapper.feedback_create_time_datetime_statistic_f2b17184_count(queryWrapper);
                                }


}
