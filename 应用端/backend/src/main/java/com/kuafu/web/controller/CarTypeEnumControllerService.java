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
import com.kuafu.web.entity.CarTypeEnum;
import com.kuafu.web.service.ICarTypeEnumService;
import com.kuafu.web.vo.CarTypeEnumPageVO;
import com.kuafu.web.vo.CarTypeEnumVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 车辆类型 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("CarTypeEnumControllerService")
@RequiredArgsConstructor
public class CarTypeEnumControllerService  {

    private final ICarTypeEnumService carTypeEnumService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( CarTypeEnumPageVO pageVO){
        IPage<CarTypeEnum> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<CarTypeEnum> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<CarTypeEnum> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(CarTypeEnum::getCarTypeEnumId);

            if(pageVO.getCarTypeEnumId() != null){
                queryWrapper.eq(CarTypeEnum::getCarTypeEnumId, pageVO.getCarTypeEnumId());
            }


        if(StringUtils.isNotEmpty(pageVO.getTypeName())) {
//                        queryWrapper.like(CarTypeEnum::getTypeName, pageVO.getTypeName());
                        String s_string = pageVO.getTypeName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(CarTypeEnum::getTypeName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }

            return ResultUtils.success(carTypeEnumService.page(page, queryWrapper));
        }


        public BaseResponse list(  CarTypeEnumVO vo){
            LambdaQueryWrapper<CarTypeEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getTypeName())) {
                            queryWrapper.like(CarTypeEnum::getTypeName, vo.getTypeName());
                }
                return ResultUtils.success(carTypeEnumService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  CarTypeEnumVO vo) {
                CarTypeEnum entity =  CarTypeEnum.builder()
                    .typeName(vo.getTypeName())
                .build();








                boolean flag =this.carTypeEnumService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("car_type_enum").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getCarTypeEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( CarTypeEnumVO vo) {
                CarTypeEnum entity =  CarTypeEnum.builder()
                    .carTypeEnumId(vo.getCarTypeEnumId())
                    .typeName(vo.getTypeName())
                .build();



                        boolean flag = this.carTypeEnumService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getCarTypeEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( CarTypeEnumVO vo ) {
                CarTypeEnum entity = this.carTypeEnumService.getById(vo.getCarTypeEnumId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( CarTypeEnumVO vo) {
                boolean flag = this.carTypeEnumService.removeById(vo.getCarTypeEnumId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
