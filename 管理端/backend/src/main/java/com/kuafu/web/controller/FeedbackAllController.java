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
import com.kuafu.web.entity.FeedbackAll;
import com.kuafu.web.service.IFeedbackAllService;
import com.kuafu.web.vo.FeedbackAllPageVO;

import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;


/**
 * <p> 反馈 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbackAll")
@Api(tags = {"反馈"})
public class FeedbackAllController  {

    private final IFeedbackAllService feedbackAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody FeedbackAllPageVO pageVO){
        IPage<FeedbackAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(pageVO.getCreateTime() != null){
            queryWrapper.eq(FeedbackAll::getCreateTime, pageVO.getCreateTime());
        }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
            queryWrapper.eq(FeedbackAll::getCity, pageVO.getCity());
        }
            if(pageVO.getActiveDays() != null){
            queryWrapper.eq(FeedbackAll::getActiveDays, pageVO.getActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
            queryWrapper.eq(FeedbackAll::getPhoneNumber, pageVO.getPhoneNumber());
        }
        if(StringUtils.isNotEmpty(pageVO.getAvatarResourceKey())) {
            queryWrapper.eq(FeedbackAll::getAvatarResourceKey, pageVO.getAvatarResourceKey());
        }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getContent())) {
            queryWrapper.eq(FeedbackAll::getContent, pageVO.getContent());
        }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
            queryWrapper.like(FeedbackAll::getRealName, pageVO.getRealName());
        }
            if(pageVO.getContinueActiveDays() != null){
            queryWrapper.eq(FeedbackAll::getContinueActiveDays, pageVO.getContinueActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
            queryWrapper.eq(FeedbackAll::getPassword, pageVO.getPassword());
        }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
            queryWrapper.eq(FeedbackAll::getCarPlate, pageVO.getCarPlate());
        }
        return ResultUtils.success(feedbackAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody FeedbackAllPageVO vo){
        LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
                if(vo.getCreateTime() != null){
            queryWrapper.eq(FeedbackAll::getCreateTime, vo.getCreateTime());
        }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getCity())) {
            queryWrapper.eq(FeedbackAll::getCity, vo.getCity());
        }
                if(vo.getActiveDays() != null){
            queryWrapper.eq(FeedbackAll::getActiveDays, vo.getActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
            queryWrapper.eq(FeedbackAll::getPhoneNumber, vo.getPhoneNumber());
        }
            if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
            queryWrapper.eq(FeedbackAll::getAvatarResourceKey, vo.getAvatarResourceKey());
        }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getContent())) {
            queryWrapper.eq(FeedbackAll::getContent, vo.getContent());
        }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
            queryWrapper.like(FeedbackAll::getRealName, vo.getRealName());
        }
                if(vo.getContinueActiveDays() != null){
            queryWrapper.eq(FeedbackAll::getContinueActiveDays, vo.getContinueActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
            queryWrapper.eq(FeedbackAll::getPassword, vo.getPassword());
        }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
            queryWrapper.eq(FeedbackAll::getCarPlate, vo.getCarPlate());
        }
        return ResultUtils.success(feedbackAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer feedbackId) {
        FeedbackAll entity = this.feedbackAllService.getById(feedbackId);
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


        /**
         * 导出excel
         */
        @PostMapping("/export")
        public void export(HttpServletResponse response, @RequestBody FeedbackAllPageVO vo) {

            LambdaQueryWrapper<FeedbackAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getUserInfoUserInfoId1() != null){
                        queryWrapper.eq(FeedbackAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                    }
                    if(vo.getCreateTime() != null){
                        queryWrapper.eq(FeedbackAll::getCreateTime, vo.getCreateTime());
                    }
                    if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(FeedbackAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getCity())) {
                        queryWrapper.eq(FeedbackAll::getCity, vo.getCity());
                    }
                    if(vo.getActiveDays() != null){
                        queryWrapper.eq(FeedbackAll::getActiveDays, vo.getActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                        queryWrapper.eq(FeedbackAll::getPhoneNumber, vo.getPhoneNumber());
                    }
                if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
                        queryWrapper.eq(FeedbackAll::getAvatarResourceKey, vo.getAvatarResourceKey());
                    }
                    if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                        queryWrapper.eq(FeedbackAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getContent())) {
                        queryWrapper.eq(FeedbackAll::getContent, vo.getContent());
                    }
                if(StringUtils.isNotEmpty(vo.getRealName())) {
                                queryWrapper.like(FeedbackAll::getRealName, vo.getRealName());
                    }
                    if(vo.getContinueActiveDays() != null){
                        queryWrapper.eq(FeedbackAll::getContinueActiveDays, vo.getContinueActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPassword())) {
                        queryWrapper.eq(FeedbackAll::getPassword, vo.getPassword());
                    }
                if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                        queryWrapper.eq(FeedbackAll::getCarPlate, vo.getCarPlate());
                    }
            List<FeedbackAll> list = feedbackAllService.list(queryWrapper);
            ExcelUtil<FeedbackAll> util = new ExcelUtil<>(FeedbackAll.class);
            util.exportExcel(response, list, "数据");
        }


}
