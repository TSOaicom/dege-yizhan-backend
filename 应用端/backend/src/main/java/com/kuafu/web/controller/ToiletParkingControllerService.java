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
import com.kuafu.web.entity.ToiletParking;
import com.kuafu.web.service.IToiletParkingService;
import com.kuafu.web.vo.ToiletParkingPageVO;
import com.kuafu.web.vo.ToiletParkingVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 厕所停车 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("ToiletParkingControllerService")
@RequiredArgsConstructor
public class ToiletParkingControllerService  {

    private final IToiletParkingService toiletParkingService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( ToiletParkingPageVO pageVO){
        IPage<ToiletParking> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<ToiletParking> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<ToiletParking> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(ToiletParking::getToiletParkingId);

            if(pageVO.getToiletParkingId() != null){
                queryWrapper.eq(ToiletParking::getToiletParkingId, pageVO.getToiletParkingId());
            }


        if(StringUtils.isNotEmpty(pageVO.getName())) {
//                        queryWrapper.like(ToiletParking::getName, pageVO.getName());
                        String s_string = pageVO.getName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(ToiletParking::getName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }


        if(StringUtils.isNotEmpty(pageVO.getLatitude())) {
                queryWrapper.eq(ToiletParking::getLatitude, pageVO.getLatitude());
            }


        if(StringUtils.isNotEmpty(pageVO.getLongitude())) {
                queryWrapper.eq(ToiletParking::getLongitude, pageVO.getLongitude());
            }


            if(pageVO.getHasParking() != null){
                queryWrapper.eq(ToiletParking::getHasParking, pageVO.getHasParking());
            }


            if(pageVO.getParkingCount() != null){
                queryWrapper.eq(ToiletParking::getParkingCount, pageVO.getParkingCount());
            }


            if(pageVO.getIsOpen() != null){
                queryWrapper.eq(ToiletParking::getIsOpen, pageVO.getIsOpen());
            }

            return ResultUtils.success(toiletParkingService.page(page, queryWrapper));
        }


        public BaseResponse list(  ToiletParkingVO vo){
            LambdaQueryWrapper<ToiletParking> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getName())) {
                            queryWrapper.like(ToiletParking::getName, vo.getName());
                }
            if(StringUtils.isNotEmpty(vo.getLatitude())) {
                    queryWrapper.eq(ToiletParking::getLatitude, vo.getLatitude());
                }
            if(StringUtils.isNotEmpty(vo.getLongitude())) {
                    queryWrapper.eq(ToiletParking::getLongitude, vo.getLongitude());
                }
                if(vo.getHasParking() != null){
                    queryWrapper.eq(ToiletParking::getHasParking, vo.getHasParking());
                }
                if(vo.getParkingCount() != null){
                    queryWrapper.eq(ToiletParking::getParkingCount, vo.getParkingCount());
                }
                if(vo.getIsOpen() != null){
                    queryWrapper.eq(ToiletParking::getIsOpen, vo.getIsOpen());
                }
                return ResultUtils.success(toiletParkingService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  ToiletParkingVO vo) {
                ToiletParking entity =  ToiletParking.builder()
                    .name(vo.getName())
                    .latitude(vo.getLatitude())
                    .longitude(vo.getLongitude())
                    .hasParking(vo.getHasParking())
                    .parkingCount(vo.getParkingCount())
                    .isOpen(vo.getIsOpen())
                .build();








                boolean flag =this.toiletParkingService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("toilet_parking").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getToiletParkingId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( ToiletParkingVO vo) {
                ToiletParking entity =  ToiletParking.builder()
                    .toiletParkingId(vo.getToiletParkingId())
                    .name(vo.getName())
                    .latitude(vo.getLatitude())
                    .longitude(vo.getLongitude())
                    .hasParking(vo.getHasParking())
                    .parkingCount(vo.getParkingCount())
                    .isOpen(vo.getIsOpen())
                .build();



                        boolean flag = this.toiletParkingService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getToiletParkingId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( ToiletParkingVO vo ) {
                ToiletParking entity = this.toiletParkingService.getById(vo.getToiletParkingId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( ToiletParkingVO vo) {
                boolean flag = this.toiletParkingService.removeById(vo.getToiletParkingId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
