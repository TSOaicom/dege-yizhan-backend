package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.MessageTypeEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> message_type_enum Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface MessageTypeEnumStatisticsMapper extends BaseMapper<MessageTypeEnum> {

                        List<Object> message_type_enum_message_type_enum_id_status_statistic_48318e25_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_type_enum_message_type_enum_id_status_statistic_0a19315b_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> message_type_enum_message_type_enum_id_status_statistic_9669aff3_count(@Param("ew") Wrapper queryWrapper);

}
