package com.kuafu.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.Wallet;
import com.kuafu.web.service.IWalletStatisticsService;
import com.kuafu.web.entity.SelectVO;

import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;





/**

 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/walletStatistics")

public class WalletStatisticsController  {

    private final IWalletStatisticsService walletStatisticsService;


    @PostMapping("wallet_statistic_972c1e1f_count")
    public BaseResponse wallet_statistic_972c1e1f_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_972c1e1f_count(queryWrapper));
    }
    @PostMapping("wallet_statistic_5f0bb06d_count")
    public BaseResponse wallet_statistic_5f0bb06d_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_5f0bb06d_count(queryWrapper));
    }
    @PostMapping("wallet_statistic_43ce330b_count")
    public BaseResponse wallet_statistic_43ce330b_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_43ce330b_count(queryWrapper));
    }
    @PostMapping("wallet_statistic_b6576e46_count")
    public BaseResponse wallet_statistic_b6576e46_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_b6576e46_count(queryWrapper));
    }
    @PostMapping("wallet_statistic_cedabba8_count")
    public BaseResponse wallet_statistic_cedabba8_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_cedabba8_count(queryWrapper));
    }
    @PostMapping("wallet_statistic_5c2132b0_count")
    public BaseResponse wallet_statistic_5c2132b0_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_statistic_5c2132b0_count(queryWrapper));
    }
    @PostMapping("wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count")
    public BaseResponse wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_type_enum_member_type_enum_id_1_status_statistic_174d5462_count(queryWrapper));
    }
    @PostMapping("wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count")
    public BaseResponse wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_type_enum_member_type_enum_id_1_status_statistic_fb152bad_count(queryWrapper));
    }
    @PostMapping("wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count")
    public BaseResponse wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_type_enum_member_type_enum_id_1_status_statistic_0f98b43f_count(queryWrapper));
    }
    @PostMapping("wallet_member_expire_datetime_statistic_7111d84f_count")
    public BaseResponse wallet_member_expire_datetime_statistic_7111d84f_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_expire_datetime_statistic_7111d84f_count(queryWrapper));
    }
    @PostMapping("wallet_member_expire_datetime_statistic_1fa6b701_count")
    public BaseResponse wallet_member_expire_datetime_statistic_1fa6b701_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_expire_datetime_statistic_1fa6b701_count(queryWrapper));
    }
    @PostMapping("wallet_member_expire_datetime_statistic_ea193847_count")
    public BaseResponse wallet_member_expire_datetime_statistic_ea193847_count(@RequestBody Wallet statisticVo) {
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( walletStatisticsService.wallet_member_expire_datetime_statistic_ea193847_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<Wallet> queryWrapper, Wallet statisticVo){
            if(statisticVo.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(Wallet::getUserInfoUserInfoId1, statisticVo.getUserInfoUserInfoId1());
            }
            if(statisticVo.getBalance() != null){
                queryWrapper.eq(Wallet::getBalance, statisticVo.getBalance());
            }
            if(statisticVo.getMemberTypeEnumMemberTypeEnumId1() != null){
                queryWrapper.eq(Wallet::getMemberTypeEnumMemberTypeEnumId1, statisticVo.getMemberTypeEnumMemberTypeEnumId1());
            }
            if(statisticVo.getMemberExpire() != null){
                queryWrapper.eq(Wallet::getMemberExpire, statisticVo.getMemberExpire());
            }
    }



}
