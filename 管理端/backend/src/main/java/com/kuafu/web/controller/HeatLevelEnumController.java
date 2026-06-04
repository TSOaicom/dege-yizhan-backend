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
import com.kuafu.web.entity.HeatLevelEnum;
import com.kuafu.web.service.IHeatLevelEnumService;
import com.kuafu.web.vo.HeatLevelEnumPageVO;
import com.kuafu.web.vo.HeatLevelEnumVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 热力等级 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/heatLevelEnum")
@Api(tags = {"热力等级"})
public class HeatLevelEnumController  {

    private final IHeatLevelEnumService heatLevelEnumService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody HeatLevelEnumPageVO pageVO){
        IPage<HeatLevelEnum> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(pageVO.getLevelName())) {
            queryWrapper.like(HeatLevelEnum::getLevelName, pageVO.getLevelName());
        }
            if(StringUtils.isNotEmpty(pageVO.getColor())) {
            queryWrapper.eq(HeatLevelEnum::getColor, pageVO.getColor());
        }
        return ResultUtils.success(heatLevelEnumService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody HeatLevelEnumVO vo){
        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getLevelName())) {
            queryWrapper.like(HeatLevelEnum::getLevelName, vo.getLevelName());
        }
            if(StringUtils.isNotEmpty(vo.getColor())) {
            queryWrapper.eq(HeatLevelEnum::getColor, vo.getColor());
        }
        return ResultUtils.success(heatLevelEnumService.list(queryWrapper));
    }


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(HeatLevelEnum::getHeatLevelEnumId);

        List<HeatLevelEnum> list =heatLevelEnumService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getHeatLevelEnumId(), l.getHeatLevelEnumId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody HeatLevelEnumVO vo) {
        HeatLevelEnum entity =  HeatLevelEnum.builder()
                    .levelName(vo.getLevelName())
                    .color(vo.getColor())
                .build();
        boolean flag =this.heatLevelEnumService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("heatLevelEnum").data(entity).build());
                }


        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody HeatLevelEnumVO vo) {
        HeatLevelEnum entity =  HeatLevelEnum.builder()
                    .heatLevelEnumId(vo.getHeatLevelEnumId())
                    .levelName(vo.getLevelName())
                    .color(vo.getColor())
                .build();
        boolean flag = this.heatLevelEnumService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("heatLevelEnum").data(entity).build());
                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer heatLevelEnumId) {
        HeatLevelEnum entity = this.heatLevelEnumService.getById(heatLevelEnumId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer heatLevelEnumId) {
        boolean flag = this.heatLevelEnumService.removeById(heatLevelEnumId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("heatLevelEnum").data( HeatLevelEnum.builder().heatLevelEnumId(heatLevelEnumId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody HeatLevelEnumPageVO pageVO) {

        LambdaQueryWrapper<HeatLevelEnum> queryWrapper = new LambdaQueryWrapper<>();

                    if(StringUtils.isNotEmpty(pageVO.getLevelName())) {
                                    queryWrapper.like(HeatLevelEnum::getLevelName, pageVO.getLevelName());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getColor())) {
                            queryWrapper.eq(HeatLevelEnum::getColor, pageVO.getColor());
                        }
            List<HeatLevelEnum> list = heatLevelEnumService.list(queryWrapper);
            ExcelUtil<HeatLevelEnum> util = new ExcelUtil<>(HeatLevelEnum.class);
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
            excelProvider.pdfData(file, HeatLevelEnum.class, heatLevelEnumService::saveBatch);
        }
        else{
            excelProvider.importData(file, HeatLevelEnum.class, heatLevelEnumService::saveBatch);
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
                        Workbook workbook = excelProvider.downloadExcelTemplate(HeatLevelEnum.class);
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
