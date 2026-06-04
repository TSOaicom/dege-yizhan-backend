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
import com.kuafu.web.entity.InviteRecordAll;
import com.kuafu.web.service.IInviteRecordAllService;
import com.kuafu.web.vo.InviteRecordAllPageVO;

import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.entity.UserInfo;


/**
 * <p> 邀请记录 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inviteRecordAll")
@Api(tags = {"邀请记录"})
public class InviteRecordAllController  {

    private final IInviteRecordAllService inviteRecordAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody InviteRecordAllPageVO pageVO){
        IPage<InviteRecordAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(pageVO.getInviteTime() != null){
            queryWrapper.eq(InviteRecordAll::getInviteTime, pageVO.getInviteTime());
        }
            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getCity())) {
            queryWrapper.eq(InviteRecordAll::getCity, pageVO.getCity());
        }
            if(pageVO.getActiveDays() != null){
            queryWrapper.eq(InviteRecordAll::getActiveDays, pageVO.getActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
            queryWrapper.eq(InviteRecordAll::getPhoneNumber, pageVO.getPhoneNumber());
        }
        if(StringUtils.isNotEmpty(pageVO.getAvatarResourceKey())) {
            queryWrapper.eq(InviteRecordAll::getAvatarResourceKey, pageVO.getAvatarResourceKey());
        }
        if(StringUtils.isNotEmpty(pageVO.getInviteePhone())) {
            queryWrapper.eq(InviteRecordAll::getInviteePhone, pageVO.getInviteePhone());
        }
            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
        }
        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
            queryWrapper.like(InviteRecordAll::getRealName, pageVO.getRealName());
        }
            if(pageVO.getContinueActiveDays() != null){
            queryWrapper.eq(InviteRecordAll::getContinueActiveDays, pageVO.getContinueActiveDays());
        }
        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
            queryWrapper.eq(InviteRecordAll::getPassword, pageVO.getPassword());
        }
        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
            queryWrapper.eq(InviteRecordAll::getCarPlate, pageVO.getCarPlate());
        }
        return ResultUtils.success(inviteRecordAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody InviteRecordAllPageVO vo){
        LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
                if(vo.getInviteTime() != null){
            queryWrapper.eq(InviteRecordAll::getInviteTime, vo.getInviteTime());
        }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getCity())) {
            queryWrapper.eq(InviteRecordAll::getCity, vo.getCity());
        }
                if(vo.getActiveDays() != null){
            queryWrapper.eq(InviteRecordAll::getActiveDays, vo.getActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
            queryWrapper.eq(InviteRecordAll::getPhoneNumber, vo.getPhoneNumber());
        }
            if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
            queryWrapper.eq(InviteRecordAll::getAvatarResourceKey, vo.getAvatarResourceKey());
        }
            if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
            queryWrapper.eq(InviteRecordAll::getInviteePhone, vo.getInviteePhone());
        }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
            queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
            queryWrapper.like(InviteRecordAll::getRealName, vo.getRealName());
        }
                if(vo.getContinueActiveDays() != null){
            queryWrapper.eq(InviteRecordAll::getContinueActiveDays, vo.getContinueActiveDays());
        }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
            queryWrapper.eq(InviteRecordAll::getPassword, vo.getPassword());
        }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
            queryWrapper.eq(InviteRecordAll::getCarPlate, vo.getCarPlate());
        }
        return ResultUtils.success(inviteRecordAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer inviteRecordId) {
        InviteRecordAll entity = this.inviteRecordAllService.getById(inviteRecordId);
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
        public void export(HttpServletResponse response, @RequestBody InviteRecordAllPageVO vo) {

            LambdaQueryWrapper<InviteRecordAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getUserInfoUserInfoId1() != null){
                        queryWrapper.eq(InviteRecordAll::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                    }
                    if(vo.getInviteTime() != null){
                        queryWrapper.eq(InviteRecordAll::getInviteTime, vo.getInviteTime());
                    }
                    if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                        queryWrapper.eq(InviteRecordAll::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getCity())) {
                        queryWrapper.eq(InviteRecordAll::getCity, vo.getCity());
                    }
                    if(vo.getActiveDays() != null){
                        queryWrapper.eq(InviteRecordAll::getActiveDays, vo.getActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                        queryWrapper.eq(InviteRecordAll::getPhoneNumber, vo.getPhoneNumber());
                    }
                if(StringUtils.isNotEmpty(vo.getAvatarResourceKey())) {
                        queryWrapper.eq(InviteRecordAll::getAvatarResourceKey, vo.getAvatarResourceKey());
                    }
                if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
                        queryWrapper.eq(InviteRecordAll::getInviteePhone, vo.getInviteePhone());
                    }
                    if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                        queryWrapper.eq(InviteRecordAll::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                    }
                if(StringUtils.isNotEmpty(vo.getRealName())) {
                                queryWrapper.like(InviteRecordAll::getRealName, vo.getRealName());
                    }
                    if(vo.getContinueActiveDays() != null){
                        queryWrapper.eq(InviteRecordAll::getContinueActiveDays, vo.getContinueActiveDays());
                    }
                if(StringUtils.isNotEmpty(vo.getPassword())) {
                        queryWrapper.eq(InviteRecordAll::getPassword, vo.getPassword());
                    }
                if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                        queryWrapper.eq(InviteRecordAll::getCarPlate, vo.getCarPlate());
                    }
            List<InviteRecordAll> list = inviteRecordAllService.list(queryWrapper);
            ExcelUtil<InviteRecordAll> util = new ExcelUtil<>(InviteRecordAll.class);
            util.exportExcel(response, list, "数据");
        }


}
