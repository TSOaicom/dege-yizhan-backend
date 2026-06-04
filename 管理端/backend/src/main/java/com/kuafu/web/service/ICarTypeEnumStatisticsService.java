package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.CarTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  car_type_enum 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface ICarTypeEnumStatisticsService extends IService<CarTypeEnum> {
        List<Object> car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(LambdaQueryWrapper queryWrapper);

        List<Object> car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(LambdaQueryWrapper queryWrapper);

        List<Object> car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(LambdaQueryWrapper queryWrapper);

}
