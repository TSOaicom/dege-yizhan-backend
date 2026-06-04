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

import com.kuafu.web.mapper.HeatLevelEnumStatisticsMapper;
import com.kuafu.web.entity.HeatLevelEnum;
import com.kuafu.web.service.IHeatLevelEnumStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> heat_level_enum 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("HeatLevelEnumStatistics")
public class HeatLevelEnumStatisticsServiceImpl extends ServiceImpl<HeatLevelEnumStatisticsMapper, HeatLevelEnum> implements IHeatLevelEnumStatisticsService {
    @Resource
    private HeatLevelEnumStatisticsMapper heatLevelEnumStaticMapper;

                        @Override
                        public List<Object> heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(LambdaQueryWrapper queryWrapper) {
                                return heatLevelEnumStaticMapper.heat_level_enum_heat_level_enum_id_status_statistic_9911c871_count(queryWrapper);
                                }


                        @Override
                        public List<Object> heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(LambdaQueryWrapper queryWrapper) {
                                return heatLevelEnumStaticMapper.heat_level_enum_heat_level_enum_id_status_statistic_05b8f60f_count(queryWrapper);
                                }


                        @Override
                        public List<Object> heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(LambdaQueryWrapper queryWrapper) {
                                return heatLevelEnumStaticMapper.heat_level_enum_heat_level_enum_id_status_statistic_7c58e626_count(queryWrapper);
                                }


}
