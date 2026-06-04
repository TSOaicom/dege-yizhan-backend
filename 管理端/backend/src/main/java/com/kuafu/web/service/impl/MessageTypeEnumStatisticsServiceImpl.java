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

import com.kuafu.web.mapper.MessageTypeEnumStatisticsMapper;
import com.kuafu.web.entity.MessageTypeEnum;
import com.kuafu.web.service.IMessageTypeEnumStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> message_type_enum 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("MessageTypeEnumStatistics")
public class MessageTypeEnumStatisticsServiceImpl extends ServiceImpl<MessageTypeEnumStatisticsMapper, MessageTypeEnum> implements IMessageTypeEnumStatisticsService {
    @Resource
    private MessageTypeEnumStatisticsMapper messageTypeEnumStaticMapper;

                        @Override
                        public List<Object> message_type_enum_message_type_enum_id_status_statistic_48318e25_count(LambdaQueryWrapper queryWrapper) {
                                return messageTypeEnumStaticMapper.message_type_enum_message_type_enum_id_status_statistic_48318e25_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(LambdaQueryWrapper queryWrapper) {
                                return messageTypeEnumStaticMapper.message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(queryWrapper);
                                }


                        @Override
                        public List<Object> message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(LambdaQueryWrapper queryWrapper) {
                                return messageTypeEnumStaticMapper.message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(queryWrapper);
                                }


}
