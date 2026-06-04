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
import com.kuafu.web.entity.CertStatusEnum;
import com.kuafu.web.service.ICertStatusEnumStatisticsService;
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
@RequestMapping("/certStatusEnumStatistics")

public class CertStatusEnumStatisticsController  {

    private final ICertStatusEnumStatisticsService certStatusEnumStatisticsService;


    @PostMapping("cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count")
    public BaseResponse cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_cert_status_enum_id_status_statistic_cd255f5d_count(queryWrapper));
    }
    @PostMapping("cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count")
    public BaseResponse cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_cert_status_enum_id_status_statistic_e6f07197_count(queryWrapper));
    }
    @PostMapping("cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count")
    public BaseResponse cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_cert_status_enum_id_status_statistic_12344ce3_count(queryWrapper));
    }
    @PostMapping("cert_status_enum_status_status_statistic_c41cd054_count")
    public BaseResponse cert_status_enum_status_status_statistic_c41cd054_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_status_status_statistic_c41cd054_count(queryWrapper));
    }
    @PostMapping("cert_status_enum_status_status_statistic_9cd65863_count")
    public BaseResponse cert_status_enum_status_status_statistic_9cd65863_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_status_status_statistic_9cd65863_count(queryWrapper));
    }
    @PostMapping("cert_status_enum_status_status_statistic_821aa606_count")
    public BaseResponse cert_status_enum_status_status_statistic_821aa606_count(@RequestBody CertStatusEnum statisticVo) {
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapperParams(queryWrapper, statisticVo);

                    
        return ResultUtils.success( certStatusEnumStatisticsService.cert_status_enum_status_status_statistic_821aa606_count(queryWrapper));
    }


    private void queryWrapperParams(LambdaQueryWrapper<CertStatusEnum> queryWrapper, CertStatusEnum statisticVo){
        if(StringUtils.isNotEmpty(statisticVo.getStatus())) {
                queryWrapper.eq(CertStatusEnum::getStatus, statisticVo.getStatus());
            }
    }



}
