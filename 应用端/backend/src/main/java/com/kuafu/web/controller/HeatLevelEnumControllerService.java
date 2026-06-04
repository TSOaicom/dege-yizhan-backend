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
import com.kuafu.web.entity.HeatLevelEnum;
import com.kuafu.web.service.IHeatLevelEnumService;
import com.kuafu.web.vo.HeatLevelEnumPageVO;
import com.kuafu.web.vo.HeatLevelEnumVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 热力等级 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("HeatLevelEnumControllerService")
@RequiredArgsConstructor
public class HeatLevelEnumControllerService  {

    private final IHeatLevelEnumService heatLevelEnumService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( HeatLevelEnumPageVO pageVO){
        IPage<HeatLevelEnum> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<HeatLevelEnum> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(HeatLevelEnum::getHeatLevelEnumId);

            if(pageVO.getHeatLevelEnumId() != null){
                queryWrapper.eq(HeatLevelEnum::getHeatLevelEnumId, pageVO.getHeatLevelEnumId());
            }


        if(StringUtils.isNotEmpty(pageVO.getLevelName())) {
//                        queryWrapper.like(HeatLevelEnum::getLevelName, pageVO.getLevelName());
                        String s_string = pageVO.getLevelName();
                String[] strings = s_string.split(" ");
                queryWrapper.and(wrapper -> {
                    for (int i = 0; i < strings.length; i++) {
                        String s = strings[i];
                        if (StringUtils.isNotEmpty(s)) {
                            final String trim = s.trim();
                            wrapper.like(HeatLevelEnum::getLevelName, trim);
                            if (i != strings.length - 1) {
                                wrapper.or();
                            }
                        }
                    }
                });
            }


        if(StringUtils.isNotEmpty(pageVO.getColor())) {
                queryWrapper.eq(HeatLevelEnum::getColor, pageVO.getColor());
            }

            return ResultUtils.success(heatLevelEnumService.page(page, queryWrapper));
        }


        public BaseResponse list(  HeatLevelEnumVO vo){
            LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getLevelName())) {
                            queryWrapper.like(HeatLevelEnum::getLevelName, vo.getLevelName());
                }
            if(StringUtils.isNotEmpty(vo.getColor())) {
                    queryWrapper.eq(HeatLevelEnum::getColor, vo.getColor());
                }
                return ResultUtils.success(heatLevelEnumService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  HeatLevelEnumVO vo) {
                HeatLevelEnum entity =  HeatLevelEnum.builder()
                    .levelName(vo.getLevelName())
                    .color(vo.getColor())
                .build();








                boolean flag =this.heatLevelEnumService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("heat_level_enum").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getHeatLevelEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( HeatLevelEnumVO vo) {
                HeatLevelEnum entity =  HeatLevelEnum.builder()
                    .heatLevelEnumId(vo.getHeatLevelEnumId())
                    .levelName(vo.getLevelName())
                    .color(vo.getColor())
                .build();



                        boolean flag = this.heatLevelEnumService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getHeatLevelEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( HeatLevelEnumVO vo ) {
                HeatLevelEnum entity = this.heatLevelEnumService.getById(vo.getHeatLevelEnumId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( HeatLevelEnumVO vo) {
                boolean flag = this.heatLevelEnumService.removeById(vo.getHeatLevelEnumId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
