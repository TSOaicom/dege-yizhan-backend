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
import com.kuafu.web.entity.MessageTypeEnum;
import com.kuafu.web.service.IMessageTypeEnumService;
import com.kuafu.web.vo.MessageTypeEnumPageVO;
import com.kuafu.web.vo.MessageTypeEnumVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 消息类型 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("MessageTypeEnumControllerService")
@RequiredArgsConstructor
public class MessageTypeEnumControllerService  {

    private final IMessageTypeEnumService messageTypeEnumService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( MessageTypeEnumPageVO pageVO){
        IPage<MessageTypeEnum> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<MessageTypeEnum> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<MessageTypeEnum> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(MessageTypeEnum::getMessageTypeEnumId);

            if(pageVO.getMessageTypeEnumId() != null){
                queryWrapper.eq(MessageTypeEnum::getMessageTypeEnumId, pageVO.getMessageTypeEnumId());
            }


        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {
//                        queryWrapper.like(MessageTypeEnum::getTypeName, pageVO.getTypeName());
                        String s_string = pageVO.getTypeName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(MessageTypeEnum::getTypeName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }

            return ResultUtils.success(messageTypeEnumService.page(page, queryWrapper));
        }


        public BaseResponse list(  MessageTypeEnumVO vo){
            LambdaQueryWrapper<MessageTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                            queryWrapper.like(MessageTypeEnum::getTypeName, vo.getTypeName());
                }
                return ResultUtils.success(messageTypeEnumService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  MessageTypeEnumVO vo) {
                MessageTypeEnum entity =  MessageTypeEnum.builder()
                    .typeName(vo.getTypeName())
                .build();








                boolean flag =this.messageTypeEnumService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("message_type_enum").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getMessageTypeEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( MessageTypeEnumVO vo) {
                MessageTypeEnum entity =  MessageTypeEnum.builder()
                    .messageTypeEnumId(vo.getMessageTypeEnumId())
                    .typeName(vo.getTypeName())
                .build();



                        boolean flag = this.messageTypeEnumService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getMessageTypeEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( MessageTypeEnumVO vo ) {
                MessageTypeEnum entity = this.messageTypeEnumService.getById(vo.getMessageTypeEnumId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( MessageTypeEnumVO vo) {
                boolean flag = this.messageTypeEnumService.removeById(vo.getMessageTypeEnumId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
