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
import com.kuafu.web.entity.Feedback;
import com.kuafu.web.service.IFeedbackService;
import com.kuafu.web.vo.FeedbackPageVO;
import com.kuafu.web.vo.FeedbackVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 反馈 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("FeedbackControllerService")
@RequiredArgsConstructor
public class FeedbackControllerService  {

    private final IFeedbackService feedbackService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( FeedbackPageVO pageVO){
        IPage<Feedback> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<Feedback> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<Feedback> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(Feedback::getFeedbackId);

            if(pageVO.getFeedbackId() != null){
                queryWrapper.eq(Feedback::getFeedbackId, pageVO.getFeedbackId());
            }


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(Feedback::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }


        if(StringUtils.isNotEmpty(pageVO.getContent())) {
                queryWrapper.eq(Feedback::getContent, pageVO.getContent());
            }


            if(pageVO.getCreateTime() != null){
                queryWrapper.eq(Feedback::getCreateTime, pageVO.getCreateTime());
            }

            return ResultUtils.success(feedbackService.page(page, queryWrapper));
        }


        public BaseResponse list(  FeedbackVO vo){
            LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(Feedback::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
            if(StringUtils.isNotEmpty(vo.getContent())) {
                    queryWrapper.eq(Feedback::getContent, vo.getContent());
                }
                if(vo.getCreateTime() != null){
                    queryWrapper.eq(Feedback::getCreateTime, vo.getCreateTime());
                }
                return ResultUtils.success(feedbackService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  FeedbackVO vo) {
                Feedback entity =  Feedback.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .content(vo.getContent())
                .build();








                boolean flag =this.feedbackService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("feedback").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getFeedbackId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( FeedbackVO vo) {
                Feedback entity =  Feedback.builder()
                    .feedbackId(vo.getFeedbackId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .content(vo.getContent())
                .build();



                        boolean flag = this.feedbackService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getFeedbackId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( FeedbackVO vo ) {
                Feedback entity = this.feedbackService.getById(vo.getFeedbackId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( FeedbackVO vo) {
                boolean flag = this.feedbackService.removeById(vo.getFeedbackId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
