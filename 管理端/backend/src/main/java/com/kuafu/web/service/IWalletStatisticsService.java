package com.kuafu.web.service;

import java.util.List;
import com.kuafu.web.entity.Wallet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p>  wallet 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IWalletStatisticsService extends IService<Wallet> {
        List<Object> wallet_statistic_972c1e1f_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_statistic_5f0bb06d_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_statistic_43ce330b_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_statistic_b6576e46_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_statistic_cedabba8_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_statistic_5c2132b0_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_expire_datetime_statistic_7111d84f_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_expire_datetime_statistic_1fa6b701_count(LambdaQueryWrapper queryWrapper);

        List<Object> wallet_member_expire_datetime_statistic_ea193847_count(LambdaQueryWrapper queryWrapper);

}
