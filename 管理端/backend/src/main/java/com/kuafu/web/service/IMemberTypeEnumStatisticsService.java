package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.MemberTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  member_type_enum 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IMemberTypeEnumStatisticsService extends IService<MemberTypeEnum> {
        List<Object> member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(LambdaQueryWrapper queryWrapper);

        List<Object> member_type_enum_member_type_enum_id_status_statistic_691c8685_count(LambdaQueryWrapper queryWrapper);

        List<Object> member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(LambdaQueryWrapper queryWrapper);

}
