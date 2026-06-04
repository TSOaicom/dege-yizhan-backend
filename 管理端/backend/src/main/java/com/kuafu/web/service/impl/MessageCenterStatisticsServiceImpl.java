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

import com.kuafu.web.mapper.MessageCenterStatisticsMapper;
import com.kuafu.web.entity.MessageCenter;
import com.kuafu.web.service.IMessageCenterStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> message_center 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("MessageCenterStatistics")
public class MessageCenterStatisticsServiceImpl extends ServiceImpl<MessageCenterStatisticsMapper, MessageCenter> implements IMessageCenterStatisticsService {
    @Resource
    private MessageCenterStatisticsMapper messageCenterStaticMapper;

                        @Override
                        public List<Object> message_center_statistic_dfa049a3_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_statistic_dfa049a3_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_statistic_475f0b61_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_statistic_475f0b61_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_statistic_f0ffe4fe_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_statistic_f0ffe4fe_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_create_time_datetime_statistic_89be4e5d_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_create_time_datetime_statistic_89be4e5d_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_create_time_datetime_statistic_4c1507ce_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_create_time_datetime_statistic_4c1507ce_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_center_create_time_datetime_statistic_27bf0915_count(LambdaQueryWrapper queryWrapper) {
                                return messageCenterStaticMapper.message_center_create_time_datetime_statistic_27bf0915_count(queryWrapper);
                                }


}
