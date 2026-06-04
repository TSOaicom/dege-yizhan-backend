package com.kuafu.web.controller;

import java.util.List;
import com.kuafu.common.entity.StaticResource;
import com.kuafu.common.resource.service.IStaticResourceService;
import com.kuafu.common.event.EventVo;
import com.kuafu.common.event.MyEventService;
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
import org.springframework.transaction.support.TransactionTemplate;
import com.kuafu.common.sensitive.SensitiveFilter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.MessageCenter;
import com.kuafu.web.service.IMessageCenterService;
import com.kuafu.web.vo.MessageCenterPageVO;
import com.kuafu.web.vo.MessageCenterVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 消息中心 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("MessageCenterControllerService")
@RequiredArgsConstructor
public class MessageCenterControllerService  {

    private final IMessageCenterService messageCenterService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( MessageCenterPageVO pageVO){
        IPage<MessageCenter> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<MessageCenter> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<MessageCenter> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(MessageCenter::getMessageCenterId);

            if(pageVO.getMessageCenterId() != null){
                queryWrapper.eq(MessageCenter::getMessageCenterId, pageVO.getMessageCenterId());
            }


        if(StringUtils.isNotEmpty(pageVO.getTitle())) {
                queryWrapper.eq(MessageCenter::getTitle, pageVO.getTitle());
            }


        if(StringUtils.isNotEmpty(pageVO.getContent())) {
                queryWrapper.eq(MessageCenter::getContent, pageVO.getContent());
            }


            if(pageVO.getTypeEnumMessageTypeEnumId1() != null){
                queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, pageVO.getTypeEnumMessageTypeEnumId1());
            }


            if(pageVO.getIsRead() != null){
                queryWrapper.eq(MessageCenter::getIsRead, pageVO.getIsRead());
            }


            if(pageVO.getCreateTime() != null){
                queryWrapper.eq(MessageCenter::getCreateTime, pageVO.getCreateTime());
            }

            return ResultUtils.success(messageCenterService.page(page, queryWrapper));
        }


        public BaseResponse list(  MessageCenterVO vo){
            LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getTitle())) {
                    queryWrapper.eq(MessageCenter::getTitle, vo.getTitle());
                }
            if(StringUtils.isNotEmpty(vo.getContent())) {
                    queryWrapper.eq(MessageCenter::getContent, vo.getContent());
                }
                if(vo.getTypeEnumMessageTypeEnumId1() != null){
                    queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
                }
                if(vo.getIsRead() != null){
                    queryWrapper.eq(MessageCenter::getIsRead, vo.getIsRead());
                }
                if(vo.getCreateTime() != null){
                    queryWrapper.eq(MessageCenter::getCreateTime, vo.getCreateTime());
                }
                return ResultUtils.success(messageCenterService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  MessageCenterVO vo) {
                MessageCenter entity =  MessageCenter.builder()
                    .title(vo.getTitle())
                    .content(vo.getContent())
                    .typeEnumMessageTypeEnumId1(vo.getTypeEnumMessageTypeEnumId1())
                    .isRead(vo.getIsRead())
                .build();








                boolean flag =this.messageCenterService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("message_center").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getMessageCenterId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( MessageCenterVO vo) {
                MessageCenter entity =  MessageCenter.builder()
                    .messageCenterId(vo.getMessageCenterId())
                    .title(vo.getTitle())
                    .content(vo.getContent())
                    .typeEnumMessageTypeEnumId1(vo.getTypeEnumMessageTypeEnumId1())
                    .isRead(vo.getIsRead())
                .build();



                        boolean flag = this.messageCenterService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getMessageCenterId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( MessageCenterVO vo ) {
                MessageCenter entity = this.messageCenterService.getById(vo.getMessageCenterId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( MessageCenterVO vo) {
                boolean flag = this.messageCenterService.removeById(vo.getMessageCenterId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
