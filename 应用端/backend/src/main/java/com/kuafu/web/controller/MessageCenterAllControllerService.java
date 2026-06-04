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
import com.kuafu.web.entity.MessageCenterAll;
import com.kuafu.web.service.IMessageCenterAllService;
import com.kuafu.web.vo.MessageCenterAllPageVO;
import com.kuafu.web.entity.SelectVo;
import com.kuafu.web.service.IMessageTypeEnumService;
import com.kuafu.web.entity.MessageTypeEnum;
import com.kuafu.web.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuafu.common.util.QueryUtils;
import org.springframework.context.annotation.Lazy;





/**
 * <p> 消息中心 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("MessageCenterAllControllerService")
public class MessageCenterAllControllerService implements IControllerService<MessageCenterAll> {


@Autowired
    private  IMessageCenterAllService messageCenterAllService;








    public BaseResponse page( MessageCenterAllPageVO pageVO){
        IPage<MessageCenterAll> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

        final QueryWrapper<MessageCenterAll> objectQueryWrapper = new QueryWrapper<>();
        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        objectQueryWrapper.orderByDesc("mc.message_center_id");
        LambdaQueryWrapper<MessageCenterAll> queryWrapper = objectQueryWrapper.lambda();


            if(pageVO.getIsRead() != null){
                queryWrapper.eq(MessageCenterAll::getIsRead, pageVO.getIsRead());
            }
        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {

                        String s_string = pageVO.getTypeName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(MessageCenterAll::getTypeName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }
            if(pageVO.getMessageCenterId() != null){
                queryWrapper.eq(MessageCenterAll::getMessageCenterId, pageVO.getMessageCenterId());
            }
            if(pageVO.getCreateTime() != null){
                queryWrapper.eq(MessageCenterAll::getCreateTime, pageVO.getCreateTime());
            }
            if(pageVO.getTypeEnumMessageTypeEnumId1() != null){
                queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, pageVO.getTypeEnumMessageTypeEnumId1());
            }
        if(StringUtils.isNotEmpty(pageVO.getTitle())) {
                queryWrapper.like(MessageCenterAll::getTitle, pageVO.getTitle());
            }
        if(StringUtils.isNotEmpty(pageVO.getContent())) {
                queryWrapper.like(MessageCenterAll::getContent, pageVO.getContent());
            }

                    messageCenterAllService.pageNew(page, pageVO, queryWrapper);
                    page.getRecords().forEach(item -> {
                    });

        page.getRecords().forEach(item -> {
        });


            return ResultUtils.success(page);
        }

        public BaseResponse list(MessageCenterAllPageVO vo){
            LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getIsRead() != null){
                    queryWrapper.eq(MessageCenterAll::getIsRead, vo.getIsRead());
                }
            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                            queryWrapper.like(MessageCenterAll::getTypeName, vo.getTypeName());
                }
                if(vo.getCreateTime() != null){
                    queryWrapper.eq(MessageCenterAll::getCreateTime, vo.getCreateTime());
                }
                if(vo.getTypeEnumMessageTypeEnumId1() != null){
                    queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getTitle())) {
                    queryWrapper.eq(MessageCenterAll::getTitle, vo.getTitle());
                }
            if(StringUtils.isNotEmpty(vo.getContent())) {
                    queryWrapper.eq(MessageCenterAll::getContent, vo.getContent());
                }
                return ResultUtils.success(messageCenterAllService.selectListNew(new PageRequest(),queryWrapper,false));
            }


            public BaseResponse get(MessageCenterAllPageVO vo) {


                LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();


                                if(vo.getIsRead() != null){
                                    queryWrapper.eq(MessageCenterAll::getIsRead, vo.getIsRead());
                                }


                            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                                            queryWrapper.like(MessageCenterAll::getTypeName, vo.getTypeName());
                                }


                                if(vo.getMessageCenterId() != null){
                                    queryWrapper.eq(MessageCenterAll::getMessageCenterId, vo.getMessageCenterId());
                                }


                                if(vo.getCreateTime() != null){
                                    queryWrapper.eq(MessageCenterAll::getCreateTime, vo.getCreateTime());
                                }


                                if(vo.getTypeEnumMessageTypeEnumId1() != null){
                                    queryWrapper.eq(MessageCenterAll::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
                                }


                            if(StringUtils.isNotEmpty(vo.getTitle())) {
                                    queryWrapper.eq(MessageCenterAll::getTitle, vo.getTitle());
                                }


                            if(StringUtils.isNotEmpty(vo.getContent())) {
                                    queryWrapper.eq(MessageCenterAll::getContent, vo.getContent());
                                }

                // MessageCenterAll entity = this.messageCenterAllService.getById(messageCenterId);
                    final Page<MessageCenterAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    messageCenterAllService.pageNew(page, vo, queryWrapper);
                    final List<MessageCenterAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? ResultUtils.success(records.get(0)) : ResultUtils.error(ErrorCode.OPERATION_ERROR);

                    // return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

            public MessageCenterAll getById(Serializable messageCenterId) {

                    LambdaQueryWrapper<MessageCenterAll> queryWrapper = new LambdaQueryWrapper<>();

                            queryWrapper.eq(MessageCenterAll::getMessageCenterId, messageCenterId);

                    final Page<MessageCenterAll> page = new Page<>();
                    page.setCurrent(1);
                    page.setSize(1);
                    messageCenterAllService.pageNew(page, new PageRequest() , queryWrapper);
                    final List<MessageCenterAll> records = page.getRecords();
                    return ObjectUtils.isNotEmpty(records) ? records.get(0) : null;

            }


        }
