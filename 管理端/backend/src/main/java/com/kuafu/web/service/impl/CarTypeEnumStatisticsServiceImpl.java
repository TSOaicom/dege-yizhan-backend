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

import com.kuafu.web.mapper.CarTypeEnumStatisticsMapper;
import com.kuafu.web.entity.CarTypeEnum;
import com.kuafu.web.service.ICarTypeEnumStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> car_type_enum 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("CarTypeEnumStatistics")
public class CarTypeEnumStatisticsServiceImpl extends ServiceImpl<CarTypeEnumStatisticsMapper, CarTypeEnum> implements ICarTypeEnumStatisticsService {
    @Resource
    private CarTypeEnumStatisticsMapper carTypeEnumStaticMapper;

                        @Override
                        public List<Object> car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(LambdaQueryWrapper queryWrapper) {
                                return carTypeEnumStaticMapper.car_type_enum_car_type_enum_id_status_statistic_cff7ec90_count(queryWrapper);
                                }


                        @Override
                        public List<Object> car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(LambdaQueryWrapper queryWrapper) {
                                return carTypeEnumStaticMapper.car_type_enum_car_type_enum_id_status_statistic_54e8df16_count(queryWrapper);
                                }


                        @Override
                        public List<Object> car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(LambdaQueryWrapper queryWrapper) {
                                return carTypeEnumStaticMapper.car_type_enum_car_type_enum_id_status_statistic_2e7d2440_count(queryWrapper);
                                }


}
