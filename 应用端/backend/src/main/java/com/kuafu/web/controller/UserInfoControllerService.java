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
import com.kuafu.web.entity.UserInfo;
import com.kuafu.web.service.IUserInfoService;
import com.kuafu.web.vo.UserInfoPageVO;
import com.kuafu.web.vo.UserInfoVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 用户信息 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("UserInfoControllerService")
@RequiredArgsConstructor
public class UserInfoControllerService  {

    private final IUserInfoService userInfoService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( UserInfoPageVO pageVO){
        IPage<UserInfo> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<UserInfo> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<UserInfo> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(UserInfo::getUserInfoId);

            if(pageVO.getUserInfoId() != null){
                queryWrapper.eq(UserInfo::getUserInfoId, pageVO.getUserInfoId());
            }


        if(StringUtils.isNotEmpty(pageVO.getPhoneNumber())) {
                queryWrapper.eq(UserInfo::getPhoneNumber, pageVO.getPhoneNumber());
            }


        if(StringUtils.isNotEmpty(pageVO.getCity())) {
                queryWrapper.eq(UserInfo::getCity, pageVO.getCity());
            }


        if(StringUtils.isNotEmpty(pageVO.getCarPlate())) {
                queryWrapper.eq(UserInfo::getCarPlate, pageVO.getCarPlate());
            }


            if(pageVO.getCarTypeEnumCarTypeEnumId1() != null){
                queryWrapper.eq(UserInfo::getCarTypeEnumCarTypeEnumId1, pageVO.getCarTypeEnumCarTypeEnumId1());
            }


        if(StringUtils.isNotEmpty(pageVO.getRealName())) {
//                        queryWrapper.like(UserInfo::getRealName, pageVO.getRealName());
                        String s_string = pageVO.getRealName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(UserInfo::getRealName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }


            if(pageVO.getCertStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserInfo::getCertStatusEnumCertStatusEnumId1, pageVO.getCertStatusEnumCertStatusEnumId1());
            }


            if(pageVO.getActiveDays() != null){
                queryWrapper.eq(UserInfo::getActiveDays, pageVO.getActiveDays());
            }


            if(pageVO.getContinueActiveDays() != null){
                queryWrapper.eq(UserInfo::getContinueActiveDays, pageVO.getContinueActiveDays());
            }


        if(StringUtils.isNotEmpty(pageVO.getPassword())) {
                queryWrapper.eq(UserInfo::getPassword, pageVO.getPassword());
            }

            return ResultUtils.success(userInfoService.page(page, queryWrapper));
        }


        public BaseResponse list(  UserInfoVO vo){
            LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getPhoneNumber())) {
                    queryWrapper.eq(UserInfo::getPhoneNumber, vo.getPhoneNumber());
                }
            if(StringUtils.isNotEmpty(vo.getCity())) {
                    queryWrapper.eq(UserInfo::getCity, vo.getCity());
                }
            if(StringUtils.isNotEmpty(vo.getCarPlate())) {
                    queryWrapper.eq(UserInfo::getCarPlate, vo.getCarPlate());
                }
                if(vo.getCarTypeEnumCarTypeEnumId1() != null){
                    queryWrapper.eq(UserInfo::getCarTypeEnumCarTypeEnumId1, vo.getCarTypeEnumCarTypeEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getRealName())) {
                            queryWrapper.like(UserInfo::getRealName, vo.getRealName());
                }
                if(vo.getCertStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(UserInfo::getCertStatusEnumCertStatusEnumId1, vo.getCertStatusEnumCertStatusEnumId1());
                }
                if(vo.getActiveDays() != null){
                    queryWrapper.eq(UserInfo::getActiveDays, vo.getActiveDays());
                }
                if(vo.getContinueActiveDays() != null){
                    queryWrapper.eq(UserInfo::getContinueActiveDays, vo.getContinueActiveDays());
                }
            if(StringUtils.isNotEmpty(vo.getPassword())) {
                    queryWrapper.eq(UserInfo::getPassword, vo.getPassword());
                }
                return ResultUtils.success(userInfoService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  UserInfoVO vo) {
                UserInfo entity =  UserInfo.builder()
                    .phoneNumber(vo.getPhoneNumber())
                    .city(vo.getCity())
                    .carPlate(vo.getCarPlate())
                    .carTypeEnumCarTypeEnumId1(vo.getCarTypeEnumCarTypeEnumId1())
                    .realName(vo.getRealName())
                    .certStatusEnumCertStatusEnumId1(vo.getCertStatusEnumCertStatusEnumId1())
                    .activeDays(vo.getActiveDays())
                    .continueActiveDays(vo.getContinueActiveDays())
                    .password(vo.getPassword())
                .build();







                boolean  f = transactionTemplate.execute(status -> {
                    try {

                boolean flag =this.userInfoService.save(entity);


                final List<StaticResource> AvatarImage = vo.getAvatar();
                if (AvatarImage  != null && !AvatarImage .isEmpty()) {
                    AvatarImage.forEach(i -> {
                        i.setRelateTableColumnName("avatar");
                        i.setRelatedTableName("user_info");
                        i.setResourceId(null);
                        i.setRelatedTableKey(entity.getUserInfoId());
                    });
                }
                staticResourceService.saveBatch(AvatarImage);




                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        status.setRollbackOnly();
                        throw e;
                    }
                });


                if(
                f
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("user_info").data(entity).build());
                }

                return
                                f
                              
                ? ResultUtils.success(entity.getUserInfoId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( UserInfoVO vo) {
                UserInfo entity =  UserInfo.builder()
                    .userInfoId(vo.getUserInfoId())
                    .phoneNumber(vo.getPhoneNumber())
                    .city(vo.getCity())
                    .carPlate(vo.getCarPlate())
                    .carTypeEnumCarTypeEnumId1(vo.getCarTypeEnumCarTypeEnumId1())
                    .realName(vo.getRealName())
                    .certStatusEnumCertStatusEnumId1(vo.getCertStatusEnumCertStatusEnumId1())
                    .activeDays(vo.getActiveDays())
                    .continueActiveDays(vo.getContinueActiveDays())
                    .password(vo.getPassword())
                .build();


                boolean  f = transactionTemplate.execute(status -> {
                    try {

                        boolean flag = this.userInfoService.saveOrUpdate(entity);



                        final List<StaticResource> AvatarImage = vo.getAvatar();

                        if (AvatarImage  != null) {
                        final LambdaQueryWrapper<StaticResource> avatarqueryWrapper = new LambdaQueryWrapper<>();
                        avatarqueryWrapper.eq(StaticResource::getRelatedTableName,"user_info").eq(StaticResource::getRelateTableColumnName,"avatar").eq(StaticResource::getRelatedTableKey,entity.getUserInfoId());
                        staticResourceService.remove(avatarqueryWrapper);
                        }


                        if (AvatarImage  != null && !AvatarImage .isEmpty()) {
                            AvatarImage.forEach(i -> {
                                i.setRelateTableColumnName("avatar");
                                i.setRelatedTableName("user_info");
                                i.setRelatedTableKey(entity.getUserInfoId());
                            });
                        }
                        staticResourceService.saveBatch(AvatarImage);




                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        status.setRollbackOnly();
                        throw e;
                    }
                });


                return  
                f
                         ? ResultUtils.success(entity.getUserInfoId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( UserInfoVO vo ) {
                UserInfo entity = this.userInfoService.getById(vo.getUserInfoId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( UserInfoVO vo) {
                boolean flag = this.userInfoService.removeById(vo.getUserInfoId());




                final LambdaQueryWrapper<StaticResource> avatarqueryWrapper = new LambdaQueryWrapper<>();
                avatarqueryWrapper.eq(StaticResource::getRelatedTableName,"user_info").eq(StaticResource::getRelateTableColumnName,"avatar").eq(StaticResource::getRelatedTableKey,vo.getUserInfoId());
                staticResourceService.remove(avatarqueryWrapper);


                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
