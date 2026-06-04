package com.kuafu.web.controller;

import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;
import com.kuafu.common.util.excel.ExcelProvider;
import org.apache.poi.ss.usermodel.Workbook;
import com.kuafu.common.file.FileUploadUtils;
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
import org.springframework.web.bind.annotation.RequestPart;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.SelectVO;
import com.kuafu.common.util.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import com.kuafu.common.sensitive.SensitiveFilter;
import com.kuafu.common.domin.StaticResource;
import com.kuafu.common.resource.service.IStaticResourceService;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import com.kuafu.common.domin.BaseResponse;
import com.kuafu.common.domin.ErrorCode;
import com.kuafu.common.domin.ResultUtils;
import com.kuafu.common.util.StringUtils;
import com.kuafu.web.entity.Wallet;
import com.kuafu.web.service.IWalletService;
import com.kuafu.web.vo.WalletPageVO;
import com.kuafu.web.vo.WalletVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 钱包 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
@Api(tags = {"钱包"})
public class WalletController  {

    private final IWalletService walletService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody WalletPageVO pageVO){
        IPage<Wallet> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

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

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody WalletVO vo){
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


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(Wallet::getWalletId);

        List<Wallet> list =walletService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getWalletId(), l.getWalletId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody WalletVO vo) {
        Wallet entity =  Wallet.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .balance(vo.getBalance())
                    .memberTypeEnumMemberTypeEnumId1(vo.getMemberTypeEnumMemberTypeEnumId1())
                    .memberExpire(vo.getMemberExpire())
                .build();
        boolean flag =this.walletService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("wallet").data(entity).build());
                }


        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody WalletVO vo) {
        Wallet entity =  Wallet.builder()
                    .walletId(vo.getWalletId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .balance(vo.getBalance())
                    .memberTypeEnumMemberTypeEnumId1(vo.getMemberTypeEnumMemberTypeEnumId1())
                    .memberExpire(vo.getMemberExpire())
                .build();
        boolean flag = this.walletService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("wallet").data(entity).build());
                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer walletId) {
        Wallet entity = this.walletService.getById(walletId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer walletId) {
        boolean flag = this.walletService.removeById(walletId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("wallet").data( Wallet.builder().walletId(walletId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody WalletPageVO pageVO) {

        LambdaQueryWrapper<Wallet> queryWrapper = new LambdaQueryWrapper<>();

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
            List<Wallet> list = walletService.list(queryWrapper);
            ExcelUtil<Wallet> util = new ExcelUtil<>(Wallet.class);
            util.exportExcel(response, list, "数据");
        }

    /**
     * 导入excel
     * @param file
     * @return
     */
    @PostMapping("/import")
    public BaseResponse importExcel(@RequestPart(name = "file") MultipartFile file)
    {
        String extension = FileUploadUtils.getExtension(file);
        if (StringUtils.equalsIgnoreCase(extension, "pdf")) {
            excelProvider.pdfData(file, Wallet.class, walletService::saveBatch);
        }
        else{
            excelProvider.importData(file, Wallet.class, walletService::saveBatch);
        }
            return ResultUtils.success("导入成功");
    }

    /**
     * 下载 excel 模版
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
                    try {
                        Workbook workbook = excelProvider.downloadExcelTemplate(Wallet.class);
                        // 返回文件流
                        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        response.setCharacterEncoding("utf-8");
                        workbook.write(response.getOutputStream());
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    }


            }
