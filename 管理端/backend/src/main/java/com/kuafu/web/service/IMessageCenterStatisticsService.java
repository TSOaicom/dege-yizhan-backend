package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.MessageCenter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  message_center 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IMessageCenterStatisticsService extends IService<MessageCenter> {
        List<Object> message_center_statistic_dfa049a3_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_statistic_475f0b61_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_statistic_f0ffe4fe_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_1ec2c82e_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_0bf25d97_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_type_enum_message_type_enum_id_1_status_statistic_dba671da_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_create_time_datetime_statistic_89be4e5d_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_create_time_datetime_statistic_4c1507ce_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_center_create_time_datetime_statistic_27bf0915_count(LambdaQueryWrapper queryWrapper);

}
