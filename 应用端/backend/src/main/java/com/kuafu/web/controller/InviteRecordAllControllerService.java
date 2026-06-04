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
import com.kuafu.web.entity.InviteRecordAll;
import com.kuafu.web.service.IInviteRecordAllService;
import com.kuafu.web.vo.InviteRecordAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 邀请记录 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("InviteRecordAllControllerService")
public class InviteRecordAllControllerService implements IControllerService<InviteRecordAll> {


@Autowired
    private  IInviteRecordAllService inviteRecordAllService;




@Autowired
@Lazy
    private  UserInfoAllControllerService UserInfoAllControllerService;




    public BaseResponse page( InviteRecordAllPageVO pageVO){
        IPage<InviteRecordAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<InviteRecordAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("ir.invite_record_id");
        LambdaQueryWrapper<InviteRecordAll> queryWrapper = objectQueryWrapper.lambda();


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
                queryWrapper.like(InviteRecordAll::getCarPlate, pageVO.getCarPlate());
            }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
                queryWrapper.like(InviteRecordAll::getCity, pageVO.getCity());
            }
        if(StringUtils.isNotEmpty(pageVO.getInviteePhone())) {
                queryWrapper.like(InviteRecordAll::getInviteePhone, pageVO.getInviteePhone());
            }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {

                        String s_string = pageVO.getRealName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(InviteRecordAll::getRealName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
                queryWrapper.like(InviteRecordAll::getPassword, pageVO.getPassword());
            }
            if(pageVO.getInviteRecordId() != null){
                queryWrapper.eq(InviteRecordAll::getInviteRecordId, pageVO.getInviteRecordId());
            }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
                queryWrapper.like(InviteRecordAll::getPhoneNumber, pageVO.getPhoneNumber());
            }
            if(pageVO.getContinueActiveDays() != null){
                queryWrapper.eq(InviteRecordAll::getContinueActiveDays, pageVO.getContinueActiveDays());
            }
            if(pageVO.getInviteTime() != null){
                queryWrapper.eq(InviteRecordAll::getInviteTime, pageVO.getInviteTime());
            }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
            }
            if(pageVO.getActiveDays() != null){
                queryWrapper.eq(InviteRecordAll::getActiveDays, pageVO.getActiveDays());
            }

                    inviteRecordAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
                        if(item.getUserInfoUserInfoId1() != null){
                            item.setAvatar(UserInfoAllControllerService.getById(item.getUserInfoUserInfoId1()).getAvatar());
                        }
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(InviteRecordAllPageVO vo){
            LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                    queryWrapper.eq(InviteRecordAll::getCarPlate, vo.getCarPlate());
                }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getCity())) {
                    queryWrapper.eq(InviteRecordAll::getCity, vo.getCity());
                }
            if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
                    queryWrapper.eq(InviteRecordAll::getInviteePhone, vo.getInviteePhone());
                }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
                            queryWrapper.like(InviteRecordAll::getRealName, vo.getRealName());
                }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
                    queryWrapper.eq(InviteRecordAll::getPassword, vo.getPassword());
                }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                    queryWrapper.eq(InviteRecordAll::getPhoneNumber, vo.getPhoneNumber());
                }
                if(vo.getContinueActiveDays() != null){
                    queryWrapper.eq(InviteRecordAll::getContinueActiveDays, vo.getContinueActiveDays());
                }
                if(vo.getInviteTime() != null){
                    queryWrapper.eq(InviteRecordAll::getInviteTime, vo.getInviteTime());
                }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                    queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                }
                if(vo.getActiveDays() != null){
                    queryWrapper.eq(InviteRecordAll::getActiveDays, vo.getActiveDays());
                }
                return ResultUtils.success(inviteRecordAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(InviteRecordAllPageVO vo) {


                LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();


                                if(vo.getUserInfoUserInfoId1() != null){
                                    queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                                    queryWrapper.eq(InviteRecordAll::getCarPlate, vo.getCarPlate());
                                }


                                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                                    queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCity())) {
                                    queryWrapper.eq(InviteRecordAll::getCity, vo.getCity());
                                }


                            if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
                                    queryWrapper.eq(InviteRecordAll::getInviteePhone, vo.getInviteePhone());
                                }


                            if(StringUtils.isNotEmpty(vo.getRealName())) {
                                            queryWrapper.like(InviteRecordAll::getRealName, vo.getRealName());
                                }


                            if(StringUtils.isNotEmpty(vo.getPassword())) {
                                    queryWrapper.eq(InviteRecordAll::getPassword, vo.getPassword());
                                }


                                if(vo.getInviteRecordId() != null){
                                    queryWrapper.eq(InviteRecordAll::getInviteRecordId, vo.getInviteRecordId());
                                }


                            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                                    queryWrapper.eq(InviteRecordAll::getPhoneNumber, vo.getPhoneNumber());
                                }


                                if(vo.getContinueActiveDays() != null){
                                    queryWrapper.eq(InviteRecordAll::getContinueActiveDays, vo.getContinueActiveDays());
                                }


                                if(vo.getInviteTime() != null){
                                    queryWrapper.eq(InviteRecordAll::getInviteTime, vo.getInviteTime());
                                }


                                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                                    queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                                }


                                if(vo.getActiveDays() != null){
                                    queryWrapper.eq(InviteRecordAll::getActiveDays, vo.getActiveDays());
                                }

                // InviteRecordAll entity = this.inviteRecordAllService.getById(inviteRecordId);
                    final Page<InviteRecordAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    inviteRecordAllService.pageNew(page, vo, queryWrapper);
                    final List<InviteRecordAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public InviteRecordAll getById(Serializable inviteRecordId) {

                    LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(InviteRecordAll::getInviteRecordId, inviteRecordId);

                    final Page<InviteRecordAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    inviteRecordAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<InviteRecordAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
