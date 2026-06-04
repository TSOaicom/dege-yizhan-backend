package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.Wallet;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> wallet Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface WalletStatisticsMapper extends BaseMapper<Wallet> {

                        List<Object> wallet_statistic_972c1e1f_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_statistic_5f0bb06d_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_statistic_43ce330b_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_statistic_b6576e46_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_statistic_cedabba8_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_statistic_5c2132b0_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_expire_datetime_statistic_7111d84f_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_expire_datetime_statistic_1fa6b701_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> wallet_member_expire_datetime_statistic_ea193847_count(@Param("ew") Wrapper queryWrapper);

}
