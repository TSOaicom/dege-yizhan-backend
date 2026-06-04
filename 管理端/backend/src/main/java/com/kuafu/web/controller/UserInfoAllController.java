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
import com.kuafu.web.entity.UserInfoAll;
import com.kuafu.web.service.IUserInfoAllService;
import com.kuafu.web.vo.UserInfoAllPageVO;

import com.kuafu.web.service.ICarTypeEnumService;
import com.kuafu.web.entity.CarTypeEnum;
import com.kuafu.web.service.ICertStatusEnumService;
import com.kuafu.web.entity.CertStatusEnum;


/**
 * <p> 用户信息 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userInfoAll")
@Api(tags = {"用户信息"})
public class UserInfoAllController  {

    private final IUserInfoAllService userInfoAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody UserInfoAllPageVO pageVO){
        IPage<UserInfoAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<UserInfoAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserInfoAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
            queryWrapper.eq(UserInfoAll::getCity, pageVO.getCity());
        }
            if(pageVO.getActiveDays() != null){
            queryWrapper.eq(UserInfoAll::getActiveDays, pageVO.getActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
            queryWrapper.eq(UserInfoAll::getPhoneNumber, pageVO.getPhoneNumber());
        }
        if(StringUtils.isNotEmpty(pageVO.getAvatarResourceKey())) {
            queryWrapper.eq(UserInfoAll::getAvatarResourceKey, pageVO.getAvatarResourceKey());
        }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(UserInfoAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
            queryWrapper.like(UserInfoAll::getRealName, pageVO.getRealName());
        }
            if(pageVO.getContinueActiveDays() != null){
            queryWrapper.eq(UserInfoAll::getContinueActiveDays, pageVO.getContinueActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
            queryWrapper.eq(UserInfoAll::getPassword, pageVO.getPassword());
        }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
            queryWrapper.eq(UserInfoAll::getCarPlate, pageVO.getCarPlate());
        }
        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {
            queryWrapper.like(UserInfoAll::getTypeName, pageVO.getTypeName());
        }
        if(StringUtils.isNotEmpty(pageVO.getStatus())) {
            queryWrapper.eq(UserInfoAll::getStatus, pageVO.getStatus());
        }
        return ResultUtils.success(userInfoAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody UserInfoAllPageVO vo){
        LambdaQueryWrapper<UserInfoAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserInfoAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getCity())) {
            queryWrapper.eq(UserInfoAll::getCity, vo.getCity());
        }
                if(vo.getActiveDays() != null){
            queryWrapper.eq(UserInfoAll::getActiveDays, vo.getActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
            queryWrapper.eq(UserInfoAll::getPhoneNumber, vo.getPhoneNumber());
        }
            if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
            queryWrapper.eq(UserInfoAll::getAvatarResourceKey, vo.getAvatarResourceKey());
        }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(UserInfoAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
            queryWrapper.like(UserInfoAll::getRealName, vo.getRealName());
        }
                if(vo.getContinueActiveDays() != null){
            queryWrapper.eq(UserInfoAll::getContinueActiveDays, vo.getContinueActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
            queryWrapper.eq(UserInfoAll::getPassword, vo.getPassword());
        }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
            queryWrapper.eq(UserInfoAll::getCarPlate, vo.getCarPlate());
        }
            if(StringUtils.isNotEmpty(vo.getTypeName())) {
            queryWrapper.like(UserInfoAll::getTypeName, vo.getTypeName());
        }
            if(StringUtils.isNotEmpty(vo.getStatus())) {
            queryWrapper.eq(UserInfoAll::getStatus, vo.getStatus());
        }
        return ResultUtils.success(userInfoAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer userInfoId) {
        UserInfoAll entity = this.userInfoAllService.getById(userInfoId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


        private final ICarTypeEnumService carTypeEnumService;

        @GetMapping("get/car_type_enum_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_car_type_enum_list() {
            List<CarTypeEnum> list = this.carTypeEnumService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getCarTypeEnumId(), p.getTypeName().toString()))
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
        public void export(HttpServletResponse response, @RequestBody UserInfoAllPageVO vo) {

            LambdaQueryWrapper<UserInfoAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(UserInfoAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getCity())) {
                        queryWrapper.eq(UserInfoAll::getCity, vo.getCity());
                    }
                    if(vo.getActiveDays() != null){
                        queryWrapper.eq(UserInfoAll::getActiveDays, vo.getActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                        queryWrapper.eq(UserInfoAll::getPhoneNumber, vo.getPhoneNumber());
                    }
                if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
                        queryWrapper.eq(UserInfoAll::getAvatarResourceKey, vo.getAvatarResourceKey());
                    }
                    if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                        queryWrapper.eq(UserInfoAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getRealName())) {
                                queryWrapper.like(UserInfoAll::getRealName, vo.getRealName());
                    }
                    if(vo.getContinueActiveDays() != null){
                        queryWrapper.eq(UserInfoAll::getContinueActiveDays, vo.getContinueActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPassword())) {
                        queryWrapper.eq(UserInfoAll::getPassword, vo.getPassword());
                    }
                if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                        queryWrapper.eq(UserInfoAll::getCarPlate, vo.getCarPlate());
                    }
                if(StringUtils.isNotEmpty(vo.getTypeName())) {
                                queryWrapper.like(UserInfoAll::getTypeName, vo.getTypeName());
                    }
                if(StringUtils.isNotEmpty(vo.getStatus())) {
                        queryWrapper.eq(UserInfoAll::getStatus, vo.getStatus());
                    }
            List<UserInfoAll> list = userInfoAllService.list(queryWrapper);
            ExcelUtil<UserInfoAll> util = new ExcelUtil<>(UserInfoAll.class);
            util.exportExcel(response, list, "数据");
        }


}
