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
import com.kuafu.web.entity.MessageCenter;
import com.kuafu.web.service.IMessageCenterService;
import com.kuafu.web.vo.MessageCenterPageVO;
import com.kuafu.web.vo.MessageCenterVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 消息中心 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/messageCenter")
@Api(tags = {"消息中心"})
public class MessageCenterController  {

    private final IMessageCenterService messageCenterService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody MessageCenterPageVO pageVO){
        IPage<MessageCenter> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(pageVO.getTitle())) {
            queryWrapper.eq(MessageCenter::getTitle, pageVO.getTitle());
        }
            if(StringUtils.isNotEmpty(pageVO.getContent())) {
            queryWrapper.eq(MessageCenter::getContent, pageVO.getContent());
        }
            if(pageVO.getTypeEnumMessageTypeEnumId1() != null){
            queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, pageVO.getTypeEnumMessageTypeEnumId1());
        }
            if(pageVO.getIsRead() != null){
            queryWrapper.eq(MessageCenter::getIsRead, pageVO.getIsRead());
        }
            if(pageVO.getCreateTime() != null){
            queryWrapper.eq(MessageCenter::getCreateTime, pageVO.getCreateTime());
        }
        return ResultUtils.success(messageCenterService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody MessageCenterVO vo){
        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

            if(StringUtils.isNotEmpty(vo.getTitle())) {
            queryWrapper.eq(MessageCenter::getTitle, vo.getTitle());
        }
            if(StringUtils.isNotEmpty(vo.getContent())) {
            queryWrapper.eq(MessageCenter::getContent, vo.getContent());
        }
                if(vo.getTypeEnumMessageTypeEnumId1() != null){
            queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, vo.getTypeEnumMessageTypeEnumId1());
        }
                if(vo.getIsRead() != null){
            queryWrapper.eq(MessageCenter::getIsRead, vo.getIsRead());
        }
                if(vo.getCreateTime() != null){
            queryWrapper.eq(MessageCenter::getCreateTime, vo.getCreateTime());
        }
        return ResultUtils.success(messageCenterService.list(queryWrapper));
    }


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(MessageCenter::getMessageCenterId);

        List<MessageCenter> list =messageCenterService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getMessageCenterId(), l.getMessageCenterId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody MessageCenterVO vo) {
        MessageCenter entity =  MessageCenter.builder()
                    .title(vo.getTitle())
                    .content(vo.getContent())
                    .typeEnumMessageTypeEnumId1(vo.getTypeEnumMessageTypeEnumId1())
                    .isRead(vo.getIsRead())
                .build();
        boolean flag =this.messageCenterService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("messageCenter").data(entity).build());
                }


        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody MessageCenterVO vo) {
        MessageCenter entity =  MessageCenter.builder()
                    .messageCenterId(vo.getMessageCenterId())
                    .title(vo.getTitle())
                    .content(vo.getContent())
                    .typeEnumMessageTypeEnumId1(vo.getTypeEnumMessageTypeEnumId1())
                    .isRead(vo.getIsRead())
                .build();
        boolean flag = this.messageCenterService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("messageCenter").data(entity).build());
                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer messageCenterId) {
        MessageCenter entity = this.messageCenterService.getById(messageCenterId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer messageCenterId) {
        boolean flag = this.messageCenterService.removeById(messageCenterId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("messageCenter").data( MessageCenter.builder().messageCenterId(messageCenterId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody MessageCenterPageVO pageVO) {

        LambdaQueryWrapper<MessageCenter> queryWrapper = new LambdaQueryWrapper<>();

                    if(StringUtils.isNotEmpty(pageVO.getTitle())) {
                            queryWrapper.eq(MessageCenter::getTitle, pageVO.getTitle());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getContent())) {
                            queryWrapper.eq(MessageCenter::getContent, pageVO.getContent());
                        }
                        if(pageVO.getTypeEnumMessageTypeEnumId1() != null){
                            queryWrapper.eq(MessageCenter::getTypeEnumMessageTypeEnumId1, pageVO.getTypeEnumMessageTypeEnumId1());
                        }
                        if(pageVO.getIsRead() != null){
                            queryWrapper.eq(MessageCenter::getIsRead, pageVO.getIsRead());
                        }
                        if(pageVO.getCreateTime() != null){
                            queryWrapper.eq(MessageCenter::getCreateTime, pageVO.getCreateTime());
                        }
            List<MessageCenter> list = messageCenterService.list(queryWrapper);
            ExcelUtil<MessageCenter> util = new ExcelUtil<>(MessageCenter.class);
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
            excelProvider.pdfData(file, MessageCenter.class, messageCenterService::saveBatch);
        }
        else{
            excelProvider.importData(file, MessageCenter.class, messageCenterService::saveBatch);
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
                        Workbook workbook = excelProvider.downloadExcelTemplate(MessageCenter.class);
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
