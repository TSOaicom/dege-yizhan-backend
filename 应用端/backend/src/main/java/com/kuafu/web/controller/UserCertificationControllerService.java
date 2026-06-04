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
import com.kuafu.web.entity.UserCertification;
import com.kuafu.web.service.IUserCertificationService;
import com.kuafu.web.vo.UserCertificationPageVO;
import com.kuafu.web.vo.UserCertificationVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 资质认证 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("UserCertificationControllerService")
@RequiredArgsConstructor
public class UserCertificationControllerService  {

    private final IUserCertificationService userCertificationService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( UserCertificationPageVO pageVO){
        IPage<UserCertification> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<UserCertification> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<UserCertification> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(UserCertification::getUserCertificationId);

            if(pageVO.getUserCertificationId() != null){
                queryWrapper.eq(UserCertification::getUserCertificationId, pageVO.getUserCertificationId());
            }


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }


            if(pageVO.getStatusEnumCertStatusEnumId1() != null){
                queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, pageVO.getStatusEnumCertStatusEnumId1());
            }


        if(StringUtils.isNotEmpty(pageVO.getRemark())) {
                queryWrapper.eq(UserCertification::getRemark, pageVO.getRemark());
            }

            return ResultUtils.success(userCertificationService.page(page, queryWrapper));
        }


        public BaseResponse list(  UserCertificationVO vo){
            LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
                if(vo.getStatusEnumCertStatusEnumId1() != null){
                    queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
                }
            if(StringUtils.isNotEmpty(vo.getRemark())) {
                    queryWrapper.eq(UserCertification::getRemark, vo.getRemark());
                }
                return ResultUtils.success(userCertificationService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  UserCertificationVO vo) {
                UserCertification entity =  UserCertification.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .statusEnumCertStatusEnumId1(vo.getStatusEnumCertStatusEnumId1())
                    .remark(vo.getRemark())
                .build();







                boolean  f = transactionTemplate.execute(status -> {
                    try {

                boolean flag =this.userCertificationService.save(entity);


                final List<StaticResource> DriverLicenseImage = vo.getDriverLicense();
                if (DriverLicenseImage  != null && !DriverLicenseImage .isEmpty()) {
                    DriverLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("driver_license");
                        i.setRelatedTableName("user_certification");
                        i.setResourceId(null);
                        i.setRelatedTableKey(entity.getUserCertificationId());
                    });
                }
                staticResourceService.saveBatch(DriverLicenseImage);


                final List<StaticResource> VehicleLicenseImage = vo.getVehicleLicense();
                if (VehicleLicenseImage  != null && !VehicleLicenseImage .isEmpty()) {
                    VehicleLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("vehicle_license");
                        i.setRelatedTableName("user_certification");
                        i.setResourceId(null);
                        i.setRelatedTableKey(entity.getUserCertificationId());
                    });
                }
                staticResourceService.saveBatch(VehicleLicenseImage);


                final List<StaticResource> TaxiLicenseImage = vo.getTaxiLicense();
                if (TaxiLicenseImage  != null && !TaxiLicenseImage .isEmpty()) {
                    TaxiLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("taxi_license");
                        i.setRelatedTableName("user_certification");
                        i.setResourceId(null);
                        i.setRelatedTableKey(entity.getUserCertificationId());
                    });
                }
                staticResourceService.saveBatch(TaxiLicenseImage);




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
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("user_certification").data(entity).build());
                }

                return
                                f
                              
                ? ResultUtils.success(entity.getUserCertificationId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( UserCertificationVO vo) {
                UserCertification entity =  UserCertification.builder()
                    .userCertificationId(vo.getUserCertificationId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .statusEnumCertStatusEnumId1(vo.getStatusEnumCertStatusEnumId1())
                    .remark(vo.getRemark())
                .build();


                boolean  f = transactionTemplate.execute(status -> {
                    try {

                        boolean flag = this.userCertificationService.saveOrUpdate(entity);



                        final List<StaticResource> DriverLicenseImage = vo.getDriverLicense();

                        if (DriverLicenseImage  != null) {
                        final LambdaQueryWrapper<StaticResource> driver_licensequeryWrapper = new LambdaQueryWrapper<>();
                        driver_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"driver_license").eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                        staticResourceService.remove(driver_licensequeryWrapper);
                        }


                        if (DriverLicenseImage  != null && !DriverLicenseImage .isEmpty()) {
                            DriverLicenseImage.forEach(i -> {
                                i.setRelateTableColumnName("driver_license");
                                i.setRelatedTableName("user_certification");
                                i.setRelatedTableKey(entity.getUserCertificationId());
                            });
                        }
                        staticResourceService.saveBatch(DriverLicenseImage);

                        final List<StaticResource> VehicleLicenseImage = vo.getVehicleLicense();

                        if (VehicleLicenseImage  != null) {
                        final LambdaQueryWrapper<StaticResource> vehicle_licensequeryWrapper = new LambdaQueryWrapper<>();
                        vehicle_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"vehicle_license").eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                        staticResourceService.remove(vehicle_licensequeryWrapper);
                        }


                        if (VehicleLicenseImage  != null && !VehicleLicenseImage .isEmpty()) {
                            VehicleLicenseImage.forEach(i -> {
                                i.setRelateTableColumnName("vehicle_license");
                                i.setRelatedTableName("user_certification");
                                i.setRelatedTableKey(entity.getUserCertificationId());
                            });
                        }
                        staticResourceService.saveBatch(VehicleLicenseImage);

                        final List<StaticResource> TaxiLicenseImage = vo.getTaxiLicense();

                        if (TaxiLicenseImage  != null) {
                        final LambdaQueryWrapper<StaticResource> taxi_licensequeryWrapper = new LambdaQueryWrapper<>();
                        taxi_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"taxi_license").eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                        staticResourceService.remove(taxi_licensequeryWrapper);
                        }


                        if (TaxiLicenseImage  != null && !TaxiLicenseImage .isEmpty()) {
                            TaxiLicenseImage.forEach(i -> {
                                i.setRelateTableColumnName("taxi_license");
                                i.setRelatedTableName("user_certification");
                                i.setRelatedTableKey(entity.getUserCertificationId());
                            });
                        }
                        staticResourceService.saveBatch(TaxiLicenseImage);




                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        status.setRollbackOnly();
                        throw e;
                    }
                });


                return  
                f
                         ? ResultUtils.success(entity.getUserCertificationId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( UserCertificationVO vo ) {
                UserCertification entity = this.userCertificationService.getById(vo.getUserCertificationId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( UserCertificationVO vo) {
                boolean flag = this.userCertificationService.removeById(vo.getUserCertificationId());




                final LambdaQueryWrapper<StaticResource> driver_licensequeryWrapper = new LambdaQueryWrapper<>();
                driver_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"driver_license").eq(StaticResource::getRelatedTableKey,vo.getUserCertificationId());
                staticResourceService.remove(driver_licensequeryWrapper);

                final LambdaQueryWrapper<StaticResource> vehicle_licensequeryWrapper = new LambdaQueryWrapper<>();
                vehicle_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"vehicle_license").eq(StaticResource::getRelatedTableKey,vo.getUserCertificationId());
                staticResourceService.remove(vehicle_licensequeryWrapper);

                final LambdaQueryWrapper<StaticResource> taxi_licensequeryWrapper = new LambdaQueryWrapper<>();
                taxi_licensequeryWrapper.eq(StaticResource::getRelatedTableName,"user_certification").eq(StaticResource::getRelateTableColumnName,"taxi_license").eq(StaticResource::getRelatedTableKey,vo.getUserCertificationId());
                staticResourceService.remove(taxi_licensequeryWrapper);


                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
