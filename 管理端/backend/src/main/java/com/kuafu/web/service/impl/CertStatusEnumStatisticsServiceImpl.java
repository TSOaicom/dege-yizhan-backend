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

import com.kuafu.web.mapper.CertStatusEnumStatisticsMapper;
import com.kuafu.web.entity.CertStatusEnum;
import com.kuafu.web.service.ICertStatusEnumStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> cert_status_enum 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("CertStatusEnumStatistics")
public class CertStatusEnumStatisticsServiceImpl extends ServiceImpl<CertStatusEnumStatisticsMapper, CertStatusEnum> implements ICertStatusEnumStatisticsService {
    @Resource
    private CertStatusEnumStatisticsMapper certStatusEnumStaticMapper;

                        @Override
                        public List<Object> cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(queryWrapper);
                                }


                        @Override
                        public List<Object> cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(queryWrapper);
                                }


                        @Override
                        public List<Object> cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(queryWrapper);
                                }


                        @Override
                        public List<Object> cert_status_enum_status_status_statistic_c41cd054_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_status_status_statistic_c41cd054_count(queryWrapper);
                                }


                        @Override
                        public List<Object> cert_status_enum_status_status_statistic_9cd65863_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_status_status_statistic_9cd65863_count(queryWrapper);
                                }


                        @Override
                        public List<Object> cert_status_enum_status_status_statistic_821aa606_count(LambdaQueryWrapper queryWrapper) {
                                return certStatusEnumStaticMapper.cert_status_enum_status_status_statistic_821aa606_count(queryWrapper);
                                }


}
