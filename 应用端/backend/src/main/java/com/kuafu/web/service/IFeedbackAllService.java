package com.kuafu.web.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.web.entity.FeedbackAll;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>  反馈 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IFeedbackAllService extends IService<FeedbackAll> {
        List<FeedbackAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<FeedbackAll> queryWrapper);
        List<FeedbackAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<FeedbackAll> queryWrapper,boolean isPage);

        long selectCount(PageRequest pageRequest, LambdaQueryWrapper<FeedbackAll> queryWrapper);

        IPage pageNew(IPage<FeedbackAll> page, PageRequest pageRequest, LambdaQueryWrapper<FeedbackAll> queryWrapper);
        }
