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
import com.kuafu.web.entity.FeedbackAll;
import com.kuafu.web.service.IFeedbackAllService;
import com.kuafu.web.vo.FeedbackAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 反馈 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("FeedbackAllControllerService")
public class FeedbackAllControllerService implements IControllerService<FeedbackAll> {


@Autowired
    private  IFeedbackAllService feedbackAllService;




@Autowired
@Lazy
    private  UserInfoAllControllerService UserInfoAllControllerService;




    public BaseResponse page( FeedbackAllPageVO pageVO){
        IPage<FeedbackAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<FeedbackAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("f.feedback_id");
        LambdaQueryWrapper<FeedbackAll> queryWrapper = objectQueryWrapper.lambda();


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
                queryWrapper.like(FeedbackAll::getCarPlate, pageVO.getCarPlate());
            }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
            }
            if(pageVO.getCreateTime() != null){
                queryWrapper.eq(FeedbackAll::getCreateTime, pageVO.getCreateTime());
            }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
                queryWrapper.like(FeedbackAll::getCity, pageVO.getCity());
            }
            if(pageVO.getFeedbackId() != null){
                queryWrapper.eq(FeedbackAll::getFeedbackId, pageVO.getFeedbackId());
            }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {

                        String s_string = pageVO.getRealName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(FeedbackAll::getRealName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
        if(StringUtils.isNotEmpty(pageVO.getContent())) {
                queryWrapper.like(FeedbackAll::getContent, pageVO.getContent());
            }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
                queryWrapper.like(FeedbackAll::getPassword, pageVO.getPassword());
            }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
                queryWrapper.like(FeedbackAll::getPhoneNumber, pageVO.getPhoneNumber());
            }
            if(pageVO.getContinueActiveDays() != null){
                queryWrapper.eq(FeedbackAll::getContinueActiveDays, pageVO.getContinueActiveDays());
            }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
            }
            if(pageVO.getActiveDays() != null){
                queryWrapper.eq(FeedbackAll::getActiveDays, pageVO.getActiveDays());
            }

                    feedbackAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
                        if(item.getUserInfoUserInfoId1() != null){
                            item.setAvatar(UserInfoAllControllerService.getById(item.getUserInfoUserInfoId1()).getAvatar());
                        }
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(FeedbackAllPageVO vo){
            LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                    queryWrapper.eq(FeedbackAll::getCarPlate, vo.getCarPlate());
                }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                }
                if(vo.getCreateTime() != null){
                    queryWrapper.eq(FeedbackAll::getCreateTime, vo.getCreateTime());
                }
            if(StringUtils.isNotEmpty(vo.getCity())) {
                    queryWrapper.eq(FeedbackAll::getCity, vo.getCity());
                }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
                            queryWrapper.like(FeedbackAll::getRealName, vo.getRealName());
                }
            if(StringUtils.isNotEmpty(vo.getContent())) {
                    queryWrapper.eq(FeedbackAll::getContent, vo.getContent());
                }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
                    queryWrapper.eq(FeedbackAll::getPassword, vo.getPassword());
                }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                    queryWrapper.eq(FeedbackAll::getPhoneNumber, vo.getPhoneNumber());
                }
                if(vo.getContinueActiveDays() != null){
                    queryWrapper.eq(FeedbackAll::getContinueActiveDays, vo.getContinueActiveDays());
                }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                    queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                }
                if(vo.getActiveDays() != null){
                    queryWrapper.eq(FeedbackAll::getActiveDays, vo.getActiveDays());
                }
                return ResultUtils.success(feedbackAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(FeedbackAllPageVO vo) {


                LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();


                                if(vo.getUserInfoUserInfoId1() != null){
                                    queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                                    queryWrapper.eq(FeedbackAll::getCarPlate, vo.getCarPlate());
                                }


                                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                                    queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                                }


                                if(vo.getCreateTime() != null){
                                    queryWrapper.eq(FeedbackAll::getCreateTime, vo.getCreateTime());
                                }


                            if(StringUtils.isNotEmpty(vo.getCity())) {
                                    queryWrapper.eq(FeedbackAll::getCity, vo.getCity());
                                }


                                if(vo.getFeedbackId() != null){
                                    queryWrapper.eq(FeedbackAll::getFeedbackId, vo.getFeedbackId());
                                }


                            if(StringUtils.isNotEmpty(vo.getRealName())) {
                                            queryWrapper.like(FeedbackAll::getRealName, vo.getRealName());
                                }


                            if(StringUtils.isNotEmpty(vo.getContent())) {
                                    queryWrapper.eq(FeedbackAll::getContent, vo.getContent());
                                }


                            if(StringUtils.isNotEmpty(vo.getPassword())) {
                                    queryWrapper.eq(FeedbackAll::getPassword, vo.getPassword());
                                }


                            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                                    queryWrapper.eq(FeedbackAll::getPhoneNumber, vo.getPhoneNumber());
                                }


                                if(vo.getContinueActiveDays() != null){
                                    queryWrapper.eq(FeedbackAll::getContinueActiveDays, vo.getContinueActiveDays());
                                }


                                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                                    queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                                }


                                if(vo.getActiveDays() != null){
                                    queryWrapper.eq(FeedbackAll::getActiveDays, vo.getActiveDays());
                                }

                // FeedbackAll entity = this.feedbackAllService.getById(feedbackId);
                    final Page<FeedbackAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    feedbackAllService.pageNew(page, vo, queryWrapper);
                    final List<FeedbackAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public FeedbackAll getById(Serializable feedbackId) {

                    LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(FeedbackAll::getFeedbackId, feedbackId);

                    final Page<FeedbackAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    feedbackAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<FeedbackAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
