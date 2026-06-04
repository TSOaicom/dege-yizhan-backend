package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.MessageTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  message_type_enum 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IMessageTypeEnumStatisticsService extends IService<MessageTypeEnum> {
        List<Object> message_type_enum_message_type_enum_id_status_statistic_48318e25_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(LambdaQueryWrapper queryWrapper);

        List<Object> message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(LambdaQueryWrapper queryWrapper);

}
