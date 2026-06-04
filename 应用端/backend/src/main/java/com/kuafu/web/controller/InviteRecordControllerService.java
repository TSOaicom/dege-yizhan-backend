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
import com.kuafu.web.entity.InviteRecord;
import com.kuafu.web.service.IInviteRecordService;
import com.kuafu.web.vo.InviteRecordPageVO;
import com.kuafu.web.vo.InviteRecordVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 邀请记录 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("InviteRecordControllerService")
@RequiredArgsConstructor
public class InviteRecordControllerService  {

    private final IInviteRecordService inviteRecordService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( InviteRecordPageVO pageVO){
        IPage<InviteRecord> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<InviteRecord> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<InviteRecord> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(InviteRecord::getInviteRecordId);

            if(pageVO.getInviteRecordId() != null){
                queryWrapper.eq(InviteRecord::getInviteRecordId, pageVO.getInviteRecordId());
            }


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }


        if(StringUtils.isNotEmpty(pageVO.getInviteePhone())) {
                queryWrapper.eq(InviteRecord::getInviteePhone, pageVO.getInviteePhone());
            }


            if(pageVO.getInviteTime() != null){
                queryWrapper.eq(InviteRecord::getInviteTime, pageVO.getInviteTime());
            }

            return ResultUtils.success(inviteRecordService.page(page, queryWrapper));
        }


        public BaseResponse list(  InviteRecordVO vo){
            LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
                    queryWrapper.eq(InviteRecord::getInviteePhone, vo.getInviteePhone());
                }
                if(vo.getInviteTime() != null){
                    queryWrapper.eq(InviteRecord::getInviteTime, vo.getInviteTime());
                }
                return ResultUtils.success(inviteRecordService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  InviteRecordVO vo) {
                InviteRecord entity =  InviteRecord.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .inviteePhone(vo.getInviteePhone())
                    .inviteTime(vo.getInviteTime())
                .build();








                boolean flag =this.inviteRecordService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("invite_record").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getInviteRecordId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( InviteRecordVO vo) {
                InviteRecord entity =  InviteRecord.builder()
                    .inviteRecordId(vo.getInviteRecordId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .inviteePhone(vo.getInviteePhone())
                    .inviteTime(vo.getInviteTime())
                .build();



                        boolean flag = this.inviteRecordService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getInviteRecordId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( InviteRecordVO vo ) {
                InviteRecord entity = this.inviteRecordService.getById(vo.getInviteRecordId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( InviteRecordVO vo) {
                boolean flag = this.inviteRecordService.removeById(vo.getInviteRecordId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
