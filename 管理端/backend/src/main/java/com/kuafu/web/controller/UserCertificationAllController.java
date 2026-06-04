package com.kuafu.web.controller;

import com.kuafu.common.util.poi.ExcelUtil;
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
import javax.servlet.http.HttpServletResponse;


import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.SelectVO;
import com.kuafu.web.entity.UserCertificationAll;
import com.kuafu.web.service.IUserCertificationAllService;
import com.kuafu.web.vo.UserCertificationAllPageVO;

import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.ICertStatusEnumService;
import com.kuafu.web.entity.CertStatusEnum;


/**
 * <p> 资质认证 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userCertificationAll")
@Api(tags = {"资质认证"})
public class UserCertificationAllController  {

    private final IUserCertificationAllService userCertificationAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody UserCertificationAllPageVO pageVO){
        IPage<UserCertificationAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
            queryWrapper.eq(UserCertificationAll::getCity, pageVO.getCity());
        }
            if(pageVO.getActiveDays() != null){
            queryWrapper.eq(UserCertificationAll::getActiveDays, pageVO.getActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
            queryWrapper.eq(UserCertificationAll::getPhoneNumber, pageVO.getPhoneNumber());
        }
        if(StringUtils.isNotEmpty(pageVO.getRemark())) {
            queryWrapper.eq(UserCertificationAll::getRemark, pageVO.getRemark());
        }
        if(StringUtils.isNotEmpty(pageVO.getAvatarResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getAvatarResourceKey, pageVO.getAvatarResourceKey());
        }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
            queryWrapper.like(UserCertificationAll::getRealName, pageVO.getRealName());
        }
        if(StringUtils.isNotEmpty(pageVO.getVehicleLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getVehicleLicenseResourceKey, pageVO.getVehicleLicenseResourceKey());
        }
            if(pageVO.getContinueActiveDays() != null){
            queryWrapper.eq(UserCertificationAll::getContinueActiveDays, pageVO.getContinueActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
            queryWrapper.eq(UserCertificationAll::getPassword, pageVO.getPassword());
        }
        if(StringUtils.isNotEmpty(pageVO.getDriverLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getDriverLicenseResourceKey, pageVO.getDriverLicenseResourceKey());
        }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
            queryWrapper.eq(UserCertificationAll::getCarPlate, pageVO.getCarPlate());
        }
            if(pageVO.getStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, pageVO.getStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getTaxiLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getTaxiLicenseResourceKey, pageVO.getTaxiLicenseResourceKey());
        }
        if(StringUtils.isNotEmpty(pageVO.getStatus())) {
            queryWrapper.eq(UserCertificationAll::getStatus, pageVO.getStatus());
        }
        return ResultUtils.success(userCertificationAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody UserCertificationAllPageVO vo){
        LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getCity())) {
            queryWrapper.eq(UserCertificationAll::getCity, vo.getCity());
        }
                if(vo.getActiveDays() != null){
            queryWrapper.eq(UserCertificationAll::getActiveDays, vo.getActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
            queryWrapper.eq(UserCertificationAll::getPhoneNumber, vo.getPhoneNumber());
        }
            if(StringUtils.isNotEmpty(vo.getRemark())) {
            queryWrapper.eq(UserCertificationAll::getRemark, vo.getRemark());
        }
            if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getAvatarResourceKey, vo.getAvatarResourceKey());
        }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
            queryWrapper.like(UserCertificationAll::getRealName, vo.getRealName());
        }
            if(StringUtils.isNotEmpty(vo.getVehicleLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getVehicleLicenseResourceKey, vo.getVehicleLicenseResourceKey());
        }
                if(vo.getContinueActiveDays() != null){
            queryWrapper.eq(UserCertificationAll::getContinueActiveDays, vo.getContinueActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
            queryWrapper.eq(UserCertificationAll::getPassword, vo.getPassword());
        }
            if(StringUtils.isNotEmpty(vo.getDriverLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getDriverLicenseResourceKey, vo.getDriverLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
            queryWrapper.eq(UserCertificationAll::getCarPlate, vo.getCarPlate());
        }
                if(vo.getStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getTaxiLicenseResourceKey())) {
            queryWrapper.eq(UserCertificationAll::getTaxiLicenseResourceKey, vo.getTaxiLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(vo.getStatus())) {
            queryWrapper.eq(UserCertificationAll::getStatus, vo.getStatus());
        }
        return ResultUtils.success(userCertificationAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer userCertificationId) {
        UserCertificationAll entity = this.userCertificationAllService.getById(userCertificationId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


        private final IUserInfoService userInfoService;

        @GetMapping("get/user_info_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_user_info_list() {
            List<UserInfo> list = this.userInfoService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getUserInfoId(), p.getPhoneNumber().toString()))
                        .collect(Collectors.toList());
            return ResultUtils.success(selectVoList);
        }
        private final ICertStatusEnumService certStatusEnumService;

        @GetMapping("get/cert_status_enum_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_cert_status_enum_list() {
            List<CertStatusEnum> list = this.certStatusEnumService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getCertStatusEnumId(), p.getStatus().toString()))
                        .collect(Collectors.toList());
            return ResultUtils.success(selectVoList);
        }


        /**
         * 导出excel
         */
        @PostMapping("/export")
        public void export(HttpServletResponse response, @RequestBody UserCertificationAllPageVO vo) {

            LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getUserInfoUserInfoId1() != null){
                        queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                    }
                    if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getCity())) {
                        queryWrapper.eq(UserCertificationAll::getCity, vo.getCity());
                    }
                    if(vo.getActiveDays() != null){
                        queryWrapper.eq(UserCertificationAll::getActiveDays, vo.getActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                        queryWrapper.eq(UserCertificationAll::getPhoneNumber, vo.getPhoneNumber());
                    }
                if(StringUtils.isNotEmpty(vo.getRemark())) {
                        queryWrapper.eq(UserCertificationAll::getRemark, vo.getRemark());
                    }
                if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
                        queryWrapper.eq(UserCertificationAll::getAvatarResourceKey, vo.getAvatarResourceKey());
                    }
                    if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                        queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getRealName())) {
                                queryWrapper.like(UserCertificationAll::getRealName, vo.getRealName());
                    }
                if(StringUtils.isNotEmpty(vo.getVehicleLicenseResourceKey())) {
                        queryWrapper.eq(UserCertificationAll::getVehicleLicenseResourceKey, vo.getVehicleLicenseResourceKey());
                    }
                    if(vo.getContinueActiveDays() != null){
                        queryWrapper.eq(UserCertificationAll::getContinueActiveDays, vo.getContinueActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPassword())) {
                        queryWrapper.eq(UserCertificationAll::getPassword, vo.getPassword());
                    }
                if(StringUtils.isNotEmpty(vo.getDriverLicenseResourceKey())) {
                        queryWrapper.eq(UserCertificationAll::getDriverLicenseResourceKey, vo.getDriverLicenseResourceKey());
                    }
                if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                        queryWrapper.eq(UserCertificationAll::getCarPlate, vo.getCarPlate());
                    }
                    if(vo.getStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getTaxiLicenseResourceKey())) {
                        queryWrapper.eq(UserCertificationAll::getTaxiLicenseResourceKey, vo.getTaxiLicenseResourceKey());
                    }
                if(StringUtils.isNotEmpty(vo.getStatus())) {
                        queryWrapper.eq(UserCertificationAll::getStatus, vo.getStatus());
                    }
            List<UserCertificationAll> list = userCertificationAllService.list(queryWrapper);
            ExcelUtil<UserCertificationAll> util = new ExcelUtil<>(UserCertificationAll.class);
            util.exportExcel(response, list, "数据");
        }


}
