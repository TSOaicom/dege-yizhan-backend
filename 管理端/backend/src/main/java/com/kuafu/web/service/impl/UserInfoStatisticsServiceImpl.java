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

import com.kuafu.web.mapper.UserInfoStatisticsMapper;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.IUserInfoStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> user_info 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("UserInfoStatistics")
public class UserInfoStatisticsServiceImpl extends ServiceImpl<UserInfoStatisticsMapper, UserInfo> implements IUserInfoStatisticsService {
    @Resource
    private UserInfoStatisticsMapper userInfoStaticMapper;

                        @Override
                        public List<Object> user_info_statistic_93e31c52_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_93e31c52_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_statistic_b65ed9bf_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_b65ed9bf_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_statistic_30868a86_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_30868a86_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_statistic_268d8230_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_268d8230_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_statistic_e7862c5d_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_e7862c5d_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_statistic_152a46d0_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_statistic_152a46d0_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_car_type_enum_car_type_enum_id_1_status_statistic_f01fb1ae_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_car_type_enum_car_type_enum_id_1_status_statistic_34e2f73b_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_car_type_enum_car_type_enum_id_1_status_statistic_ffe967b0_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_ecdf3c00_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_6afbda8f_count(queryWrapper);
                                }


                        @Override
                        public List<Object> user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(LambdaQueryWrapper queryWrapper) {
                                return userInfoStaticMapper.user_info_cert_status_enum_cert_status_enum_id_1_status_statistic_79fd138f_count(queryWrapper);
                                }


}
