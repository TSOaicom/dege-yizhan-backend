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

import com.kuafu.web.mapper.UserCertificationStatisticsMapper;
import com.kuafu.web.entity.UserCertification;
import com.kuafu.web.service.IUserCertificationStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> user_certification 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("UserCertificationStatistics")
public class UserCertificationStatisticsServiceImpl extends ServiceImpl<UserCertificationStatisticsMapper, UserCertification> implements IUserCertificationStatisticsService {
    @Resource
    private UserCertificationStatisticsMapper userCertificationStaticMapper;

                        @Override
                        public List<Object> user_certification_statistic_7c28f5da_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_7c28f5da_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_statistic_a5058d87_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_a5058d87_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_statistic_c2590f13_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_c2590f13_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_statistic_27b11a15_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_27b11a15_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_statistic_589059e4_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_589059e4_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_statistic_40f91fb8_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_statistic_40f91fb8_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_status_enum_cert_status_enum_id_1_status_statistic_45c03617_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_status_enum_cert_status_enum_id_1_status_statistic_f48e1c34_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(LambdaQueryWrapper queryWrapper) {
                                return userCertificationStaticMapper.user_certification_status_enum_cert_status_enum_id_1_status_statistic_83318247_count(queryWrapper);
                                }


}
