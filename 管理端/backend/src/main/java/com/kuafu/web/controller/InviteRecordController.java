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
import com.kuafu.web.entity.InviteRecord;
import com.kuafu.web.service.IInviteRecordService;
import com.kuafu.web.vo.InviteRecordPageVO;
import com.kuafu.web.vo.InviteRecordVO;
import com.kuafu.web.event.MyEventService;
import com.kuafu.web.event.EventVo;
/**
 * <p> 邀请记录 接口 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inviteRecord")
@Api(tags = {"邀请记录"})
public class InviteRecordController  {

    private final IInviteRecordService inviteRecordService;

    private final MyEventService myEventService;

    private final ExcelProvider excelProvider;
    private final IStaticResourceService staticResourceService;

    @PostMapping("page")
    @ApiOperation("分页")
    public BaseResponse page( @RequestBody InviteRecordPageVO pageVO){
        IPage<InviteRecord> page = new Page<>(pageVO.getCurrent(), pageVO.getPageSize());
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

            if(pageVO.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
        }
            if(StringUtils.isNotEmpty(pageVO.getInviteePhone())) {
            queryWrapper.eq(InviteRecord::getInviteePhone, pageVO.getInviteePhone());
        }
            if(pageVO.getInviteTime() != null){
            queryWrapper.eq(InviteRecord::getInviteTime, pageVO.getInviteTime());
        }
        return ResultUtils.success(inviteRecordService.page(page, queryWrapper));
    }

    @PostMapping("list")
    @ApiOperation("列表")
    public BaseResponse list( @RequestBody InviteRecordVO vo){
        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

                if(vo.getUserInfoUserInfoId1() != null){
            queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, vo.getUserInfoUserInfoId1());
        }
            if(StringUtils.isNotEmpty(vo.getInviteePhone())) {
            queryWrapper.eq(InviteRecord::getInviteePhone, vo.getInviteePhone());
        }
                if(vo.getInviteTime() != null){
            queryWrapper.eq(InviteRecord::getInviteTime, vo.getInviteTime());
        }
        return ResultUtils.success(inviteRecordService.list(queryWrapper));
    }


            @PostMapping("get_select_list")
            @ApiOperation("获取所有的下拉列表")
            public BaseResponse get_select_list( ){
                LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.select(InviteRecord::getInviteRecordId);

        List<InviteRecord> list =inviteRecordService.list(queryWrapper);
        final List<SelectVO> selectVOS = list.stream().map(l -> new SelectVO(l.getInviteRecordId(), l.getInviteRecordId().toString())).collect(Collectors.toList());
        return ResultUtils.success(selectVOS);

                }

    @PostMapping("add")
    @ApiOperation("新增")
    @SensitiveFilter
    public BaseResponse add( @RequestBody InviteRecordVO vo) {
        InviteRecord entity =  InviteRecord.builder()
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .inviteePhone(vo.getInviteePhone())
                    .inviteTime(vo.getInviteTime())
                .build();
        boolean flag =this.inviteRecordService.save(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("add").tableName("inviteRecord").data(entity).build());
                }


        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    @PutMapping("update")
    @ApiOperation("更新")
    @SensitiveFilter
    public BaseResponse update( @RequestBody InviteRecordVO vo) {
        InviteRecord entity =  InviteRecord.builder()
                    .inviteRecordId(vo.getInviteRecordId())
                    .userInfoUserInfoId1(vo.getUserInfoUserInfoId1())
                    .inviteePhone(vo.getInviteePhone())
                    .inviteTime(vo.getInviteTime())
                .build();
        boolean flag = this.inviteRecordService.updateById(entity);
                if (flag) {
                    myEventService.publishEvent(EventVo.builder().model("update").tableName("inviteRecord").data(entity).build());
                }



        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @GetMapping("get/{id}")
    @ApiOperation("根据Id查询")
    public BaseResponse get(@PathVariable(value="id") Integer inviteRecordId) {
        InviteRecord entity = this.inviteRecordService.getById(inviteRecordId);
        return entity!=null ? ResultUtils.success(entity) : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除")
    public BaseResponse delete(@PathVariable(value="id") Integer inviteRecordId) {
        boolean flag = this.inviteRecordService.removeById(inviteRecordId);
        if (flag) {
            myEventService.publishEvent(EventVo.builder().model("delete").tableName("inviteRecord").data( InviteRecord.builder().inviteRecordId(inviteRecordId).build() ).build());
        }
        return flag ? ResultUtils.success() : ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }

    /**
     * 导出excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody InviteRecordPageVO pageVO) {

        LambdaQueryWrapper<InviteRecord> queryWrapper = new LambdaQueryWrapper<>();

                        if(pageVO.getUserInfoUserInfoId1() != null){
                            queryWrapper.eq(InviteRecord::getUserInfoUserInfoId1, pageVO.getUserInfoUserInfoId1());
                        }
                    if(StringUtils.isNotEmpty(pageVO.getInviteePhone())) {
                            queryWrapper.eq(InviteRecord::getInviteePhone, pageVO.getInviteePhone());
                        }
                        if(pageVO.getInviteTime() != null){
                            queryWrapper.eq(InviteRecord::getInviteTime, pageVO.getInviteTime());
                        }
            List<InviteRecord> list = inviteRecordService.list(queryWrapper);
            ExcelUtil<InviteRecord> util = new ExcelUtil<>(InviteRecord.class);
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
            excelProvider.pdfData(file, InviteRecord.class, inviteRecordService::saveBatch);
        }
        else{
            excelProvider.importData(file, InviteRecord.class, inviteRecordService::saveBatch);
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
                        Workbook workbook = excelProvider.downloadExcelTemplate(InviteRecord.class);
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
