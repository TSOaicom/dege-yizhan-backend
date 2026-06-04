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
import com.kuafu.web.entity.MessageCenterAll;
import com.kuafu.web.service.IMessageCenterAllService;
import com.kuafu.web.vo.MessageCenterAllPageVO;

import com.kuafu.web.service.IMessageTypeEnumService;
import com.kuafu.web.entity.MessageTypeEnum;


/**
 * <p> 消息中心 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/messageCenterAll")
@Api(tags = {"消息中心"})
public class MessageCenterAllController  {

    private final IMessageCenterAllService messageCenterAllService;

    @PostMapping("page")
    @ApiOperation("全字段分页")
    public BaseResponse page(@RequestBody MessageCenterAllPageVO pageVO){
        IPage<MessageCenterAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getCreateTime() != null){
            queryWrapper.eq(MessageCenterAll::getCreateTime, pageVO.getCreateTime());
        }
            if(pageVO.getTypeEnumMessageTypeEnumId1() != null){
            queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, pageVO.getTypeEnumMessageTypeEnumId1());
        }
            if(pageVO.getIsRead() != null){
            queryWrapper.eq(MessageCenterAll::getIsRead, pageVO.getIsRead());
        }
        if(StringUtils.isNotEmpty(pageVO.getTitle())) {
            queryWrapper.eq(MessageCenterAll::getTitle, pageVO.getTitle());
        }
        if(StringUtils.isNotEmpty(pageVO.getContent())) {
            queryWrapper.eq(MessageCenterAll::getContent, pageVO.getContent());
        }
        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {
            queryWrapper.like(MessageCenterAll::getTypeName, pageVO.getTypeName());
        }
        return ResultUtils.success(messageCenterAllService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("全字段列表")
    public BaseResponse list(@RequestBody MessageCenterAllPageVO vo){
        LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getCreateTime() != null){
            queryWrapper.eq(MessageCenterAll::getCreateTime, vo.getCreateTime());
        }
                if(vo.getTypeEnumMessageTypeEnumId1() != null){
            queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
        }
                if(vo.getIsRead() != null){
            queryWrapper.eq(MessageCenterAll::getIsRead, vo.getIsRead());
        }
            if(StringUtils.isNotEmpty(vo.getTitle())) {
            queryWrapper.eq(MessageCenterAll::getTitle, vo.getTitle());
        }
            if(StringUtils.isNotEmpty(vo.getContent())) {
            queryWrapper.eq(MessageCenterAll::getContent, vo.getContent());
        }
            if(StringUtils.isNotEmpty(vo.getTypeName())) {
            queryWrapper.like(MessageCenterAll::getTypeName, vo.getTypeName());
        }
        return ResultUtils.success(messageCenterAllService.list(queryWrapper));
    }

    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer messageCenterId) {
        MessageCenterAll entity = this.messageCenterAllService.getById(messageCenterId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


        private final IMessageTypeEnumService messageTypeEnumService;

        @GetMapping("get/message_type_enum_list")
        @ApiOperation("查询下拉框的外键信息")
        public BaseResponse get_message_type_enum_list() {
            List<MessageTypeEnum> list = this.messageTypeEnumService.list();
            final List<SelectVO> selectVoList = list.stream().map(p ->
                new SelectVO(p.getMessageTypeEnumId(), p.getTypeName().toString()))
                        .collect(Collectors.toList());
            return ResultUtils.success(selectVoList);
        }


        /**
         * 导出excel
         */
        @PostMapping("/export")
        public void export(HttpServletResponse response, @RequestBody MessageCenterAllPageVO vo) {

            LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();

                    if(vo.getCreateTime() != null){
                        queryWrapper.eq(MessageCenterAll::getCreateTime, vo.getCreateTime());
                    }
                    if(vo.getTypeEnumMessageTypeEnumId1() != null){
                        queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
                    }
                    if(vo.getIsRead() != null){
                        queryWrapper.eq(MessageCenterAll::getIsRead, vo.getIsRead());
                    }
                if(StringUtils.isNotEmpty(vo.getTitle())) {
                        queryWrapper.eq(MessageCenterAll::getTitle, vo.getTitle());
                    }
                if(StringUtils.isNotEmpty(vo.getContent())) {
                        queryWrapper.eq(MessageCenterAll::getContent, vo.getContent());
                    }
                if(StringUtils.isNotEmpty(vo.getTypeName())) {
                                queryWrapper.like(MessageCenterAll::getTypeName, vo.getTypeName());
                    }
            List<MessageCenterAll> list = messageCenterAllService.list(queryWrapper);
            ExcelUtil<MessageCenterAll> util = new ExcelUtil<>(MessageCenterAll.class);
            util.exportExcel(response, list, "数据");
        }


}
