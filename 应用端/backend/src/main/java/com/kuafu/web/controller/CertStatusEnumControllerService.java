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
import com.kuafu.web.entity.CertStatusEnum;
import com.kuafu.web.service.ICertStatusEnumService;
import com.kuafu.web.vo.CertStatusEnumPageVO;
import com.kuafu.web.vo.CertStatusEnumVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 认证状态 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("CertStatusEnumControllerService")
@RequiredArgsConstructor
public class CertStatusEnumControllerService  {

    private final ICertStatusEnumService certStatusEnumService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( CertStatusEnumPageVO pageVO){
        IPage<CertStatusEnum> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<CertStatusEnum> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<CertStatusEnum> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(CertStatusEnum::getCertStatusEnumId);

            if(pageVO.getCertStatusEnumId() != null){
                queryWrapper.eq(CertStatusEnum::getCertStatusEnumId, pageVO.getCertStatusEnumId());
            }


        if(StringUtils.isNotEmpty(pageVO.getStatus())) {
                queryWrapper.eq(CertStatusEnum::getStatus, pageVO.getStatus());
            }

            return ResultUtils.success(certStatusEnumService.page(page, queryWrapper));
        }


        public BaseResponse list(  CertStatusEnumVO vo){
            LambdaQueryWrapper<CertStatusEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getStatus())) {
                    queryWrapper.eq(CertStatusEnum::getStatus, vo.getStatus());
                }
                return ResultUtils.success(certStatusEnumService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  CertStatusEnumVO vo) {
                CertStatusEnum entity =  CertStatusEnum.builder()
                    .status(vo.getStatus())
                .build();








                boolean flag =this.certStatusEnumService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("cert_status_enum").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getCertStatusEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( CertStatusEnumVO vo) {
                CertStatusEnum entity =  CertStatusEnum.builder()
                    .certStatusEnumId(vo.getCertStatusEnumId())
                    .status(vo.getStatus())
                .build();



                        boolean flag = this.certStatusEnumService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getCertStatusEnumId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( CertStatusEnumVO vo ) {
                CertStatusEnum entity = this.certStatusEnumService.getById(vo.getCertStatusEnumId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( CertStatusEnumVO vo) {
                boolean flag = this.certStatusEnumService.removeById(vo.getCertStatusEnumId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
