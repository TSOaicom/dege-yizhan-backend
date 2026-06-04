package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.InviteRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  invite_record 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IInviteRecordStatisticsService extends IService<InviteRecord> {
        List<Object> invite_record_statistic_31bed8f4_count(LambdaQueryWrapper queryWrapper);

        List<Object> invite_record_statistic_a629efa5_count(LambdaQueryWrapper queryWrapper);

        List<Object> invite_record_statistic_72eba71b_count(LambdaQueryWrapper queryWrapper);

        List<Object> invite_record_invite_time_datetime_statistic_9e15a676_count(LambdaQueryWrapper queryWrapper);

        List<Object> invite_record_invite_time_datetime_statistic_31df2134_count(LambdaQueryWrapper queryWrapper);

        List<Object> invite_record_invite_time_datetime_statistic_8a084807_count(LambdaQueryWrapper queryWrapper);

}
