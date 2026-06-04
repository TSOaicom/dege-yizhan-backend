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
import com.kuafu.web.entity.UserCertificationAll;
import com.kuafu.web.service.IUserCertificationAllService;
import com.kuafu.web.vo.UserCertificationAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.ICertStatusEnumService;
import com.kuafu.web.entity.CertStatusEnum;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 资质认证 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("UserCertificationAllControllerService")
public class UserCertificationAllControllerService implements IControllerService<UserCertificationAll> {


@Autowired
    private  IUserCertificationAllService userCertificationAllService;




@Autowired
@Lazy
    private  UserInfoAllControllerService UserInfoAllControllerService;




    public BaseResponse page( UserCertificationAllPageVO pageVO){
        IPage<UserCertificationAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<UserCertificationAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("uc.user_certification_id");
        LambdaQueryWrapper<UserCertificationAll> queryWrapper = objectQueryWrapper.lambda();


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
                queryWrapper.like(UserCertificationAll::getCarPlate, pageVO.getCarPlate());
            }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
                queryWrapper.like(UserCertificationAll::getCity, pageVO.getCity());
            }
        if(StringUtils.isNotEmpty(pageVO.getRemark())) {
                queryWrapper.like(UserCertificationAll::getRemark, pageVO.getRemark());
            }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {

                        String s_string = pageVO.getRealName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(UserCertificationAll::getRealName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getUserCertificationId() != null){
                queryWrapper.eq(UserCertificationAll::getUserCertificationId, pageVO.getUserCertificationId());
            }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
                queryWrapper.like(UserCertificationAll::getPassword, pageVO.getPassword());
            }
            if(pageVO.getStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, pageVO.getStatusEnumCertStatusEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
                queryWrapper.like(UserCertificationAll::getPhoneNumber, pageVO.getPhoneNumber());
            }
            if(pageVO.getContinueActiveDays() != null){
                queryWrapper.eq(UserCertificationAll::getContinueActiveDays, pageVO.getContinueActiveDays());
            }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
            }
            if(pageVO.getActiveDays() != null){
                queryWrapper.eq(UserCertificationAll::getActiveDays, pageVO.getActiveDays());
            }
        if(StringUtils.isNotEmpty(pageVO.getStatus())) {
                queryWrapper.like(UserCertificationAll::getStatus, pageVO.getStatus());
            }

                    userCertificationAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
                        if(item.getUserInfoUserInfoId1() != null){
                            item.setAvatar(UserInfoAllControllerService.getById(item.getUserInfoUserInfoId1()).getAvatar());
                        }
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(UserCertificationAllPageVO vo){
            LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                    queryWrapper.eq(UserCertificationAll::getCarPlate, vo.getCarPlate());
                }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getCity())) {
                    queryWrapper.eq(UserCertificationAll::getCity, vo.getCity());
                }
            if(StringUtils.isNotEmpty(vo.getRemark())) {
                    queryWrapper.eq(UserCertificationAll::getRemark, vo.getRemark());
                }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
                            queryWrapper.like(UserCertificationAll::getRealName, vo.getRealName());
                }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
                    queryWrapper.eq(UserCertificationAll::getPassword, vo.getPassword());
                }
                if(vo.getStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                    queryWrapper.eq(UserCertificationAll::getPhoneNumber, vo.getPhoneNumber());
                }
                if(vo.getContinueActiveDays() != null){
                    queryWrapper.eq(UserCertificationAll::getContinueActiveDays, vo.getContinueActiveDays());
                }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                    queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                }
                if(vo.getActiveDays() != null){
                    queryWrapper.eq(UserCertificationAll::getActiveDays, vo.getActiveDays());
                }
            if(StringUtils.isNotEmpty(vo.getStatus())) {
                    queryWrapper.eq(UserCertificationAll::getStatus, vo.getStatus());
                }
                return ResultUtils.success(userCertificationAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(UserCertificationAllPageVO vo) {


                LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();


                                if(vo.getUserInfoUserInfoId1() != null){
                                    queryWrapper.eq(UserCertificationAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                                    queryWrapper.eq(UserCertificationAll::getCarPlate, vo.getCarPlate());
                                }


                                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                                    queryWrapper.eq(UserCertificationAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCity())) {
                                    queryWrapper.eq(UserCertificationAll::getCity, vo.getCity());
                                }


                            if(StringUtils.isNotEmpty(vo.getRemark())) {
                                    queryWrapper.eq(UserCertificationAll::getRemark, vo.getRemark());
                                }


                            if(StringUtils.isNotEmpty(vo.getRealName())) {
                                            queryWrapper.like(UserCertificationAll::getRealName, vo.getRealName());
                                }


                                if(vo.getUserCertificationId() != null){
                                    queryWrapper.eq(UserCertificationAll::getUserCertificationId, vo.getUserCertificationId());
                                }


                            if(StringUtils.isNotEmpty(vo.getPassword())) {
                                    queryWrapper.eq(UserCertificationAll::getPassword, vo.getPassword());
                                }


                                if(vo.getStatusEnumCertStatusEnumId1() != null){
                                    queryWrapper.eq(UserCertificationAll::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                                    queryWrapper.eq(UserCertificationAll::getPhoneNumber, vo.getPhoneNumber());
                                }


                                if(vo.getContinueActiveDays() != null){
                                    queryWrapper.eq(UserCertificationAll::getContinueActiveDays, vo.getContinueActiveDays());
                                }


                                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                                    queryWrapper.eq(UserCertificationAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                                }


                                if(vo.getActiveDays() != null){
                                    queryWrapper.eq(UserCertificationAll::getActiveDays, vo.getActiveDays());
                                }


                            if(StringUtils.isNotEmpty(vo.getStatus())) {
                                    queryWrapper.eq(UserCertificationAll::getStatus, vo.getStatus());
                                }

                // UserCertificationAll entity = this.userCertificationAllService.getById(userCertificationId);
                    final Page<UserCertificationAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    userCertificationAllService.pageNew(page, vo, queryWrapper);
                    final List<UserCertificationAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public UserCertificationAll getById(Serializable userCertificationId) {

                    LambdaQueryWrapper<UserCertificationAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(UserCertificationAll::getUserCertificationId, userCertificationId);

                    final Page<UserCertificationAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    userCertificationAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<UserCertificationAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
