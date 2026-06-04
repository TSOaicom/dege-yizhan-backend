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
import com.kuafu.web.entity.ToiletParking;
import com.kuafu.web.service.IToiletParkingService;
import com.kuafu.web.vo.ToiletParkingPageVO;
import com.kuafu.web.vo.ToiletParkingVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 厕所停车 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/toiletParking")
@Api(tags = {"厕所停车"})
public class ToiletParkingController  {

    private final IToiletParkingService toiletParkingService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody ToiletParkingPageVO pageVO){
        IPage<ToiletParking> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<ToiletParking> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(pageVO.getName())) {
            queryWrapper.like(ToiletParking::getName, pageVO.getName());
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

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody ToiletParkingVO vo){
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


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<ToiletParking> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(ToiletParking::getToiletParkingId);

        List<ToiletParking> list =toiletParkingService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getToiletParkingId(), l.getToiletParkingId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody ToiletParkingVO vo) {
        ToiletParking entity =  ToiletParking.builder()
                    .name(vo.getName())
                    .latitude(vo.getLatitude())
                    .longitude(vo.getLongitude())
                    .hasParking(vo.getHasParking())
                    .parkingCount(vo.getParkingCount())
                    .isOpen(vo.getIsOpen())
                .build();
        boolean flag =this.toiletParkingService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("toiletParking").data(entity).build());
                }


        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody ToiletParkingVO vo) {
        ToiletParking entity =  ToiletParking.builder()
                    .toiletParkingId(vo.getToiletParkingId())
                    .name(vo.getName())
                    .latitude(vo.getLatitude())
                    .longitude(vo.getLongitude())
                    .hasParking(vo.getHasParking())
                    .parkingCount(vo.getParkingCount())
                    .isOpen(vo.getIsOpen())
                .build();
        boolean flag = this.toiletParkingService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("toiletParking").data(entity).build());
                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer toiletParkingId) {
        ToiletParking entity = this.toiletParkingService.getById(toiletParkingId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer toiletParkingId) {
        boolean flag = this.toiletParkingService.removeById(toiletParkingId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("toiletParking").data( ToiletParking.builder().toiletParkingId(toiletParkingId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody ToiletParkingPageVO pageVO) {

        LambdaQueryWrapper<ToiletParking> queryWrapper = new LambdaQueryWrapper<>();

                    if(StringUtils.isNotEmpty(pageVO.getName())) {
                                    queryWrapper.like(ToiletParking::getName, pageVO.getName());
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
            List<ToiletParking> list = toiletParkingService.list(queryWrapper);
            ExcelUtil<ToiletParking> util = new ExcelUtil<>(ToiletParking.class);
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
            excelProvider.pdfData(file, ToiletParking.class, toiletParkingService::saveBatch);
        }
        else{
            excelProvider.importData(file, ToiletParking.class, toiletParkingService::saveBatch);
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
                        Workbook workbook = excelProvider.downloadExcelTemplate(ToiletParking.class);
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
