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

import com.kuafu.web.mapper.InviteRecordStatisticsMapper;
import com.kuafu.web.entity.InviteRecord;
import com.kuafu.web.service.IInviteRecordStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> invite_record 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("InviteRecordStatistics")
public class InviteRecordStatisticsServiceImpl extends ServiceImpl<InviteRecordStatisticsMapper, InviteRecord> implements IInviteRecordStatisticsService {
    @Resource
    private InviteRecordStatisticsMapper inviteRecordStaticMapper;

                        @Override
                        public List<Object> invite_record_statistic_31bed8f4_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_statistic_31bed8f4_count(queryWrapper);
                                }


                        @Override
                        public List<Object> invite_record_statistic_a629efa5_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_statistic_a629efa5_count(queryWrapper);
                                }


                        @Override
                        public List<Object> invite_record_statistic_72eba71b_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_statistic_72eba71b_count(queryWrapper);
                                }


                        @Override
                        public List<Object> invite_record_invite_time_datetime_statistic_9e15a676_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_invite_time_datetime_statistic_9e15a676_count(queryWrapper);
                                }


                        @Override
                        public List<Object> invite_record_invite_time_datetime_statistic_31df2134_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_invite_time_datetime_statistic_31df2134_count(queryWrapper);
                                }


                        @Override
                        public List<Object> invite_record_invite_time_datetime_statistic_8a084807_count(LambdaQueryWrapper queryWrapper) {
                                return inviteRecordStaticMapper.invite_record_invite_time_datetime_statistic_8a084807_count(queryWrapper);
                                }


}
