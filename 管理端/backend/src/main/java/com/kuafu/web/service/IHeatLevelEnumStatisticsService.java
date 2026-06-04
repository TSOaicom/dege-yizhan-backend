package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.HeatLevelEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  heat_level_enum 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IHeatLevelEnumStatisticsService extends IService<HeatLevelEnum> {
        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(LambdaQueryWrapper queryWrapper);

        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(LambdaQueryWrapper queryWrapper);

        List<Object> heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(LambdaQueryWrapper queryWrapper);

}
