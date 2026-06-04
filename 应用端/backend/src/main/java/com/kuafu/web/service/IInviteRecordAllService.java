package com.kuafu.web.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.web.entity.InviteRecordAll;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>  邀请记录 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IInviteRecordAllService extends IService<InviteRecordAll> {
        List<InviteRecordAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<InviteRecordAll> queryWrapper);
        List<InviteRecordAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<InviteRecordAll> queryWrapper,boolean isPage);

        long selectCount(PageRequest pageRequest, LambdaQueryWrapper<InviteRecordAll> queryWrapper);

        IPage pageNew(IPage<InviteRecordAll> page, PageRequest pageRequest, LambdaQueryWrapper<InviteRecordAll> queryWrapper);
        }
