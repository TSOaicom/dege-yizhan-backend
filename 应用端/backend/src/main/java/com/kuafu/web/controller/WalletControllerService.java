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
import com.kuafu.web.entity.Wallet;
import com.kuafu.web.service.IWalletService;
import com.kuafu.web.vo.WalletPageVO;
import com.kuafu.web.vo.WalletVO;
import com.kuafu.common.util.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p> 钱包 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Service("WalletControllerService")
@RequiredArgsConstructor
public class WalletControllerService  {

    private final IWalletService walletService;

    private final IStaticResourceService staticResourceService;

    private final TransactionTemplate transactionTemplate;

    private final MyEventService myEventService;



    public BaseResponse page( WalletPageVO pageVO){
        IPage<Wallet> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());

         final QueryWrapper<Wallet> objectQueryWrapper = new QueryWrapper<>();

        QueryUtils.getQueryWrapper(objectQueryWrapper,pageVO);
        LambdaQueryWrapper<Wallet> queryWrapper = objectQueryWrapper.lambda();
        queryWrapper.orderByDesc(Wallet::getWalletId);

            if(pageVO.getWalletId() != null){
                queryWrapper.eq(Wallet::getWalletId, pageVO.getWalletId());
            }


            if(pageVO.getUserInfoUserInfoId1() != null){
                queryWrapper.eq(Wallet::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
            }


            if(pageVO.getBalance() != null){
                queryWrapper.eq(Wallet::getBalance, pageVO.getBalance());
            }


            if(pageVO.getMemberTypeEnumMemberTypeEnumId1() != null){
                queryWrapper.eq(Wallet::getMemberTypeEnumMemberTypeEnumId1, pageVO.getMemberTypeEnumMemberTypeEnumId1());
            }


            if(pageVO.getMemberExpire() != null){
                queryWrapper.eq(Wallet::getMemberExpire, pageVO.getMemberExpire());
            }

            return ResultUtils.success(walletService.page(page, queryWrapper));
        }


        public BaseResponse list(  WalletVO vo){
            LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
                    queryWrapper.eq(Wallet::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
                }
                if(vo.getBalance() != null){
                    queryWrapper.eq(Wallet::getBalance, vo.getBalance());
                }
                if(vo.getMemberTypeEnumMemberTypeEnumId1() != null){
                    queryWrapper.eq(Wallet::getMemberTypeEnumMemberTypeEnumId1, vo.getMemberTypeEnumMemberTypeEnumId1());
                }
                if(vo.getMemberExpire() != null){
                    queryWrapper.eq(Wallet::getMemberExpire, vo.getMemberExpire());
                }
                return ResultUtils.success(walletService.list(queryWrapper));
            }


            @SensitiveFilter
            public BaseResponse add(  WalletVO vo) {
                Wallet entity =  Wallet.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .balance(vo.getBalance())
                    .memberTypeEnumMemberTypeEnumId1(vo.getMemberTypeEnumMemberTypeEnumId1())
                    .memberExpire(vo.getMemberExpire())
                .build();








                boolean flag =this.walletService.save(entity);






                if(flag
                        ){
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("wallet").data(entity).build());
                }

                return
flag

                ? ResultUtils.success(entity.getWalletId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }


            @SensitiveFilter
            public BaseResponse update( WalletVO vo) {
                Wallet entity =  Wallet.builder()
                    .walletId(vo.getWalletId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .balance(vo.getBalance())
                    .memberTypeEnumMemberTypeEnumId1(vo.getMemberTypeEnumMemberTypeEnumId1())
                    .memberExpire(vo.getMemberExpire())
                .build();



                        boolean flag = this.walletService.saveOrUpdate(entity);








                return  flag
                         ? ResultUtils.success(entity.getWalletId()) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse get( WalletVO vo ) {
                Wallet entity = this.walletService.getById(vo.getWalletId());
                return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }



            public BaseResponse delete( WalletVO vo) {
                boolean flag = this.walletService.removeById(vo.getWalletId());





                return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
            }

        }
