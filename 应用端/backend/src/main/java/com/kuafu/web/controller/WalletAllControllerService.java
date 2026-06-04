package com.kuafu.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import java.io.Serializable;
import com.kuafu.common.login.IControllerService;

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
import org.apache.commons.lang3.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kuafu.login.annotation.*;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.WalletAll;
import com.kuafu.web.service.IWalletAllService;
import com.kuafu.web.vo.WalletAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.IMemberTypeEnumService;
import com.kuafu.web.entity.MemberTypeEnum;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 钱包 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("WalletAllControllerService")
public class WalletAllControllerService implements IControllerService<WalletAll> {


@Autowired
    private  IWalletAllService walletAllService;




@Autowired
@Lazy
    private  UserInfoAllControllerService UserInfoAllControllerService;




    public BaseResponse page( WalletAllPageVO pageVO){
        IPage<WalletAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<WalletAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("w.wallet_id");
        LambdaQueryWrapper<WalletAll> queryWrapper = objectQueryWrapper.lambda();


        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {

                        String s_string = pageVO.getTypeName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(WalletAll::getTypeName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
                queryWrapper.like(WalletAll::getCarPlate, pageVO.getCarPlate());
            }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
                queryWrapper.like(WalletAll::getCity, pageVO.getCity());
            }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {

                        String s_string = pageVO.getRealName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(WalletAll::getRealName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getMemberTypeEnumMemberTypeEnumId1() != null){
                queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, pageVO.getMemberTypeEnumMemberTypeEnumId1());
            }
            if(pageVO.getWalletId() != null){
                queryWrapper.eq(WalletAll::getWalletId, pageVO.getWalletId());
            }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
                queryWrapper.like(WalletAll::getPassword, pageVO.getPassword());
            }
            if(pageVO.getBalance() != null){
                queryWrapper.eq(WalletAll::getBalance, pageVO.getBalance());
            }
            if(pageVO.getMemberExpire() != null){
                queryWrapper.eq(WalletAll::getMemberExpire, pageVO.getMemberExpire());
            }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
                queryWrapper.like(WalletAll::getPhoneNumber, pageVO.getPhoneNumber());
            }
            if(pageVO.getContinueActiveDays() != null){
                queryWrapper.eq(WalletAll::getContinueActiveDays, pageVO.getContinueActiveDays());
            }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
            }
            if(pageVO.getActiveDays() != null){
                queryWrapper.eq(WalletAll::getActiveDays, pageVO.getActiveDays());
            }

                    walletAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
                        if(item.getUserInfoUserInfoId1() != null){
                            item.setAvatar(UserInfoAllControllerService.getById(item.getUserInfoUserInfoId1()).getAvatar());
                        }
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(WalletAllPageVO vo){
            LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                            queryWrapper.like(WalletAll::getTypeName, vo.getTypeName());
                }
                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                    queryWrapper.eq(WalletAll::getCarPlate, vo.getCarPlate());
                }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getCity())) {
                    queryWrapper.eq(WalletAll::getCity, vo.getCity());
                }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
                            queryWrapper.like(WalletAll::getRealName, vo.getRealName());
                }
                if(vo.getMemberTypeEnumMemberTypeEnumId1() != null){
                    queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, vo.getMemberTypeEnumMemberTypeEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
                    queryWrapper.eq(WalletAll::getPassword, vo.getPassword());
                }
                if(vo.getBalance() != null){
                    queryWrapper.eq(WalletAll::getBalance, vo.getBalance());
                }
                if(vo.getMemberExpire() != null){
                    queryWrapper.eq(WalletAll::getMemberExpire, vo.getMemberExpire());
                }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                    queryWrapper.eq(WalletAll::getPhoneNumber, vo.getPhoneNumber());
                }
                if(vo.getContinueActiveDays() != null){
                    queryWrapper.eq(WalletAll::getContinueActiveDays, vo.getContinueActiveDays());
                }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                    queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                }
                if(vo.getActiveDays() != null){
                    queryWrapper.eq(WalletAll::getActiveDays, vo.getActiveDays());
                }
                return ResultUtils.success(walletAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(WalletAllPageVO vo) {


                LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();


                            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                                            queryWrapper.like(WalletAll::getTypeName, vo.getTypeName());
                                }


                                if(vo.getUserInfoUserInfoId1() != null){
                                    queryWrapper.eq(WalletAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                                    queryWrapper.eq(WalletAll::getCarPlate, vo.getCarPlate());
                                }


                                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                                    queryWrapper.eq(WalletAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCity())) {
                                    queryWrapper.eq(WalletAll::getCity, vo.getCity());
                                }


                            if(StringUtils.isNotEmpty(vo.getRealName())) {
                                            queryWrapper.like(WalletAll::getRealName, vo.getRealName());
                                }


                                if(vo.getMemberTypeEnumMemberTypeEnumId1() != null){
                                    queryWrapper.eq(WalletAll::getMemberTypeEnumMemberTypeEnumId1, vo.getMemberTypeEnumMemberTypeEnumId1());
                                }


                                if(vo.getWalletId() != null){
                                    queryWrapper.eq(WalletAll::getWalletId, vo.getWalletId());
                                }


                            if(StringUtils.isNotEmpty(vo.getPassword())) {
                                    queryWrapper.eq(WalletAll::getPassword, vo.getPassword());
                                }


                                if(vo.getBalance() != null){
                                    queryWrapper.eq(WalletAll::getBalance, vo.getBalance());
                                }


                                if(vo.getMemberExpire() != null){
                                    queryWrapper.eq(WalletAll::getMemberExpire, vo.getMemberExpire());
                                }


                            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                                    queryWrapper.eq(WalletAll::getPhoneNumber, vo.getPhoneNumber());
                                }


                                if(vo.getContinueActiveDays() != null){
                                    queryWrapper.eq(WalletAll::getContinueActiveDays, vo.getContinueActiveDays());
                                }


                                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                                    queryWrapper.eq(WalletAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                                }


                                if(vo.getActiveDays() != null){
                                    queryWrapper.eq(WalletAll::getActiveDays, vo.getActiveDays());
                                }

                // WalletAll entity = this.walletAllService.getById(walletId);
                    final Page<WalletAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    walletAllService.pageNew(page, vo, queryWrapper);
                    final List<WalletAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public WalletAll getById(Serializable walletId) {

                    LambdaQueryWrapper<WalletAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(WalletAll::getWalletId, walletId);

                    final Page<WalletAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    walletAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<WalletAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
