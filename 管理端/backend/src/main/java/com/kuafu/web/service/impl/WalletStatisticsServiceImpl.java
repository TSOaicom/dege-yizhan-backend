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

import com.kuafu.web.mapper.WalletStatisticsMapper;
import com.kuafu.web.entity.Wallet;
import com.kuafu.web.service.IWalletStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> wallet 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("WalletStatistics")
public class WalletStatisticsServiceImpl extends ServiceImpl<WalletStatisticsMapper, Wallet> implements IWalletStatisticsService {
    @Resource
    private WalletStatisticsMapper walletStaticMapper;

                        @Override
                        public List<Object> wallet_statistic_972c1e1f_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_972c1e1f_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_statistic_5f0bb06d_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_5f0bb06d_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_statistic_43ce330b_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_43ce330b_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_statistic_b6576e46_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_b6576e46_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_statistic_cedabba8_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_cedabba8_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_statistic_5c2132b0_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_statistic_5c2132b0_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_expire_datetime_statistic_7111d84f_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_expire_datetime_statistic_7111d84f_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_expire_datetime_statistic_1fa6b701_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_expire_datetime_statistic_1fa6b701_count(queryWrapper);
                                }


                        @Override
                        public List<Object> wallet_member_expire_datetime_statistic_ea193847_count(LambdaQueryWrapper queryWrapper) {
                                return walletStaticMapper.wallet_member_expire_datetime_statistic_ea193847_count(queryWrapper);
                                }


}
