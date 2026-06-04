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
import com.kuafu.web.entity.WalletAll;
import com.kuafu.web.service.IWalletAllService;
import com.kuafu.web.vo.WalletAllPageVO;

import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.IMemberTypeEnumService;
import com.kuafu.web.entity.MemberTypeEnum;


/**
 * <p> 钱包 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/walletAll")
@Api(tags = {"钱包"})
public class WalletAllController  {

    private final IWalletAllService walletAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody WalletAllPageVO pageVO){
        IPage<WalletAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(pageVO.getMemberExpire() != null){
            queryWrapper.eq(WalletAll::getMemberExpire, pageVO.getMemberExpire());
        }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
            queryWrapper.eq(WalletAll::getCity, pageVO.getCity());
        }
            if(pageVO.getMemberTypeEnumMemberTypeEnumId1() != null){
            queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, pageVO.getMemberTypeEnumMemberTypeEnumId1());
        }
            if(pageVO.getActiveDays() != null){
            queryWrapper.eq(WalletAll::getActiveDays, pageVO.getActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
            queryWrapper.eq(WalletAll::getPhoneNumber, pageVO.getPhoneNumber());
        }
        if(StringUtils.isNotEmpty(pageVO.getAvatarResourceKey())) {
            queryWrapper.eq(WalletAll::getAvatarResourceKey, pageVO.getAvatarResourceKey());
        }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
            queryWrapper.like(WalletAll::getRealName, pageVO.getRealName());
        }
            if(pageVO.getContinueActiveDays() != null){
            queryWrapper.eq(WalletAll::getContinueActiveDays, pageVO.getContinueActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
            queryWrapper.eq(WalletAll::getPassword, pageVO.getPassword());
        }
            if(pageVO.getBalance() != null){
            queryWrapper.eq(WalletAll::getBalance, pageVO.getBalance());
        }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
            queryWrapper.eq(WalletAll::getCarPlate, pageVO.getCarPlate());
        }
        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {
            queryWrapper.like(WalletAll::getTypeName, pageVO.getTypeName());
        }
        return ResultUtils.success(walletAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody WalletAllPageVO vo){
        LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
                if(vo.getMemberExpire() != null){
            queryWrapper.eq(WalletAll::getMemberExpire, vo.getMemberExpire());
        }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getCity())) {
            queryWrapper.eq(WalletAll::getCity, vo.getCity());
        }
                if(vo.getMemberTypeEnumMemberTypeEnumId1() != null){
            queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, vo.getMemberTypeEnumMemberTypeEnumId1());
        }
                if(vo.getActiveDays() != null){
            queryWrapper.eq(WalletAll::getActiveDays, vo.getActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
            queryWrapper.eq(WalletAll::getPhoneNumber, vo.getPhoneNumber());
        }
            if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
            queryWrapper.eq(WalletAll::getAvatarResourceKey, vo.getAvatarResourceKey());
        }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
            queryWrapper.like(WalletAll::getRealName, vo.getRealName());
        }
                if(vo.getContinueActiveDays() != null){
            queryWrapper.eq(WalletAll::getContinueActiveDays, vo.getContinueActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
            queryWrapper.eq(WalletAll::getPassword, vo.getPassword());
        }
                if(vo.getBalance() != null){
            queryWrapper.eq(WalletAll::getBalance, vo.getBalance());
        }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
            queryWrapper.eq(WalletAll::getCarPlate, vo.getCarPlate());
        }
            if(StringUtils.isNotEmpty(vo.getTypeName())) {
            queryWrapper.like(WalletAll::getTypeName, vo.getTypeName());
        }
        return ResultUtils.success(walletAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer walletId) {
        WalletAll entity = this.walletAllService.getById(walletId);
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
        private final IMemberTypeEnumService memberTypeEnumService;

        @GetMapping("get/member_type_enum_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_member_type_enum_list() {
            List<MemberTypeEnum> list = this.memberTypeEnumService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getMemberTypeEnumId(), p.getTypeName().toString()))
                        .collect(Collectors.toList());
            return ResultUtils.success(selectVoList);
        }


        /**
         * 导出excel
         */
        @PostMapping("/export")
        public void export(HttpServletResponse response, @RequestBody WalletAllPageVO vo) {

            LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getUserInfoUserInfoId1() != null){
                        queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                    }
                    if(vo.getMemberExpire() != null){
                        queryWrapper.eq(WalletAll::getMemberExpire, vo.getMemberExpire());
                    }
                    if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getCity())) {
                        queryWrapper.eq(WalletAll::getCity, vo.getCity());
                    }
                    if(vo.getMemberTypeEnumMemberTypeEnumId1() != null){
                        queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, vo.getMemberTypeEnumMemberTypeEnumId1());
                    }
                    if(vo.getActiveDays() != null){
                        queryWrapper.eq(WalletAll::getActiveDays, vo.getActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                        queryWrapper.eq(WalletAll::getPhoneNumber, vo.getPhoneNumber());
                    }
                if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
                        queryWrapper.eq(WalletAll::getAvatarResourceKey, vo.getAvatarResourceKey());
                    }
                    if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                        queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getRealName())) {
                                queryWrapper.like(WalletAll::getRealName, vo.getRealName());
                    }
                    if(vo.getContinueActiveDays() != null){
                        queryWrapper.eq(WalletAll::getContinueActiveDays, vo.getContinueActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPassword())) {
                        queryWrapper.eq(WalletAll::getPassword, vo.getPassword());
                    }
                    if(vo.getBalance() != null){
                        queryWrapper.eq(WalletAll::getBalance, vo.getBalance());
                    }
                if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                        queryWrapper.eq(WalletAll::getCarPlate, vo.getCarPlate());
                    }
                if(StringUtils.isNotEmpty(vo.getTypeName())) {
                                queryWrapper.like(WalletAll::getTypeName, vo.getTypeName());
                    }
            List<WalletAll> list = walletAllService.list(queryWrapper);
            ExcelUtil<WalletAll> util = new ExcelUtil<>(WalletAll.class);
            util.exportExcel(response, list, "数据");
        }


}
