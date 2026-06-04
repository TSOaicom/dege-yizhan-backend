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
import com.kuafu.web.entity.UserCertification;
import com.kuafu.web.service.IUserCertificationService;
import com.kuafu.web.vo.UserCertificationPageVO;
import com.kuafu.web.vo.UserCertificationVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 资质认证 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userCertification")
@Api(tags = {"资质认证"})
public class UserCertificationController  {

    private final IUserCertificationService userCertificationService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody UserCertificationPageVO pageVO){
        IPage<UserCertification> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(StringUtils.isNotEmpty(pageVO.getDriverLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getDriverLicenseResourceKey, pageVO.getDriverLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(pageVO.getVehicleLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getVehicleLicenseResourceKey, pageVO.getVehicleLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(pageVO.getTaxiLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getTaxiLicenseResourceKey, pageVO.getTaxiLicenseResourceKey());
        }
            if(pageVO.getStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, pageVO.getStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(pageVO.getRemark())) {
            queryWrapper.eq(UserCertification::getRemark, pageVO.getRemark());
        }
        return ResultUtils.success(userCertificationService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody UserCertificationVO vo){
        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
            if(StringUtils.isNotEmpty(vo.getDriverLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getDriverLicenseResourceKey, vo.getDriverLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(vo.getVehicleLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getVehicleLicenseResourceKey, vo.getVehicleLicenseResourceKey());
        }
            if(StringUtils.isNotEmpty(vo.getTaxiLicenseResourceKey())) {
            queryWrapper.eq(UserCertification::getTaxiLicenseResourceKey, vo.getTaxiLicenseResourceKey());
        }
                if(vo.getStatusEnumCertStatusEnumId1() != null){
            queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, vo.getStatusEnumCertStatusEnumId1());
        }
            if(StringUtils.isNotEmpty(vo.getRemark())) {
            queryWrapper.eq(UserCertification::getRemark, vo.getRemark());
        }
        return ResultUtils.success(userCertificationService.list(queryWrapper));
    }


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(UserCertification::getUserCertificationId);

        List<UserCertification> list =userCertificationService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getUserCertificationId(), l.getUserCertificationId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody UserCertificationVO vo) {
        UserCertification entity =  UserCertification.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                  .driverLicenseResourceKey(vo.getDriverLicenseResourceKey() == null ? "default_resource_key" : vo.getDriverLicenseResourceKey())
                  .vehicleLicenseResourceKey(vo.getVehicleLicenseResourceKey() == null ? "default_resource_key" : vo.getVehicleLicenseResourceKey())
                  .taxiLicenseResourceKey(vo.getTaxiLicenseResourceKey() == null ? "default_resource_key" : vo.getTaxiLicenseResourceKey())
                    .statusEnumCertStatusEnumId1(vo.getStatusEnumCertStatusEnumId1())
                    .remark(vo.getRemark())
                .build();
        boolean flag =this.userCertificationService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("userCertification").data(entity).build());
                }

                final List<StaticResource> DriverLicenseImage = vo.getDriverLicense();
                if (DriverLicenseImage  != null && !DriverLicenseImage.isEmpty()) {
                    DriverLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("driver_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        i.setResourcePath(i.getResourceName());
                    });

                    staticResourceService.saveBatch(DriverLicenseImage);

                }
                final List<StaticResource> VehicleLicenseImage = vo.getVehicleLicense();
                if (VehicleLicenseImage  != null && !VehicleLicenseImage.isEmpty()) {
                    VehicleLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("vehicle_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        i.setResourcePath(i.getResourceName());
                    });

                    staticResourceService.saveBatch(VehicleLicenseImage);

                }
                final List<StaticResource> TaxiLicenseImage = vo.getTaxiLicense();
                if (TaxiLicenseImage  != null && !TaxiLicenseImage.isEmpty()) {
                    TaxiLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("taxi_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        i.setResourcePath(i.getResourceName());
                    });

                    staticResourceService.saveBatch(TaxiLicenseImage);

                }

        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody UserCertificationVO vo) {
        UserCertification entity =  UserCertification.builder()
                    .userCertificationId(vo.getUserCertificationId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .driverLicenseResourceKey(vo.getDriverLicenseResourceKey())
                    .vehicleLicenseResourceKey(vo.getVehicleLicenseResourceKey())
                    .taxiLicenseResourceKey(vo.getTaxiLicenseResourceKey())
                    .statusEnumCertStatusEnumId1(vo.getStatusEnumCertStatusEnumId1())
                    .remark(vo.getRemark())
                .build();
        boolean flag = this.userCertificationService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("userCertification").data(entity).build());
                }

                final LambdaQueryWrapper<StaticResource> driver_licensequeryWrapper = new LambdaQueryWrapper<>();
                driver_licensequeryWrapper
                        .eq(StaticResource::getRelatedTableName,"user_certification")
                        .eq(StaticResource::getRelateTableColumnName,"driver_license")
                        .eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                staticResourceService.remove(driver_licensequeryWrapper);


                final List<StaticResource> DriverLicenseImage = vo.getDriverLicense();
                if (DriverLicenseImage  != null && !DriverLicenseImage.isEmpty()) {
                    DriverLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("driver_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        if(StringUtils.isNotEmpty(i.getResourceName())) {
                            i.setResourcePath(i.getResourceName());
                        }
                    });

                    staticResourceService.saveBatch(DriverLicenseImage);

                }

                final LambdaQueryWrapper<StaticResource> vehicle_licensequeryWrapper = new LambdaQueryWrapper<>();
                vehicle_licensequeryWrapper
                        .eq(StaticResource::getRelatedTableName,"user_certification")
                        .eq(StaticResource::getRelateTableColumnName,"vehicle_license")
                        .eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                staticResourceService.remove(vehicle_licensequeryWrapper);


                final List<StaticResource> VehicleLicenseImage = vo.getVehicleLicense();
                if (VehicleLicenseImage  != null && !VehicleLicenseImage.isEmpty()) {
                    VehicleLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("vehicle_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        if(StringUtils.isNotEmpty(i.getResourceName())) {
                            i.setResourcePath(i.getResourceName());
                        }
                    });

                    staticResourceService.saveBatch(VehicleLicenseImage);

                }

                final LambdaQueryWrapper<StaticResource> taxi_licensequeryWrapper = new LambdaQueryWrapper<>();
                taxi_licensequeryWrapper
                        .eq(StaticResource::getRelatedTableName,"user_certification")
                        .eq(StaticResource::getRelateTableColumnName,"taxi_license")
                        .eq(StaticResource::getRelatedTableKey,entity.getUserCertificationId());
                staticResourceService.remove(taxi_licensequeryWrapper);


                final List<StaticResource> TaxiLicenseImage = vo.getTaxiLicense();
                if (TaxiLicenseImage  != null && !TaxiLicenseImage.isEmpty()) {
                    TaxiLicenseImage.forEach(i -> {
                        i.setRelateTableColumnName("taxi_license");
                        i.setRelatedTableName("user_certification");
                        i.setRelatedTableKey(entity.getUserCertificationId());
                        if(StringUtils.isNotEmpty(i.getResourceName())) {
                            i.setResourcePath(i.getResourceName());
                        }
                    });

                    staticResourceService.saveBatch(TaxiLicenseImage);

                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer userCertificationId) {
        UserCertification entity = this.userCertificationService.getById(userCertificationId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer userCertificationId) {
        boolean flag = this.userCertificationService.removeById(userCertificationId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("userCertification").data( UserCertification.builder().userCertificationId(userCertificationId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody UserCertificationPageVO pageVO) {

        LambdaQueryWrapper<UserCertification> queryWrapper = new LambdaQueryWrapper<>();

                        if(pageVO.getUserInfoUserInfoId1() != null){
                            queryWrapper.eq(UserCertification::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getDriverLicenseResourceKey())) {
                            queryWrapper.eq(UserCertification::getDriverLicenseResourceKey, pageVO.getDriverLicenseResourceKey());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getVehicleLicenseResourceKey())) {
                            queryWrapper.eq(UserCertification::getVehicleLicenseResourceKey, pageVO.getVehicleLicenseResourceKey());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getTaxiLicenseResourceKey())) {
                            queryWrapper.eq(UserCertification::getTaxiLicenseResourceKey, pageVO.getTaxiLicenseResourceKey());
                        }
                        if(pageVO.getStatusEnumCertStatusEnumId1() != null){
                            queryWrapper.eq(UserCertification::getStatusEnumCertStatusEnumId1, pageVO.getStatusEnumCertStatusEnumId1());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getRemark())) {
                            queryWrapper.eq(UserCertification::getRemark, pageVO.getRemark());
                        }
            List<UserCertification> list = userCertificationService.list(queryWrapper);
            ExcelUtil<UserCertification> util = new ExcelUtil<>(UserCertification.class);
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
            excelProvider.pdfData(file, UserCertification.class, userCertificationService::saveBatch);
        }
        else{
            excelProvider.importData(file, UserCertification.class, userCertificationService::saveBatch);
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
                        Workbook workbook = excelProvider.downloadExcelTemplate(UserCertification.class);
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
