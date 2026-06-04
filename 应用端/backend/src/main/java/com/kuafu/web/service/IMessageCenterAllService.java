package com.kuafu.web.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.web.entity.MessageCenterAll;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>  消息中心 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IMessageCenterAllService extends IService<MessageCenterAll> {
        List<MessageCenterAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<MessageCenterAll> queryWrapper);
        List<MessageCenterAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<MessageCenterAll> queryWrapper,boolean isPage);

        long selectCount(PageRequest pageRequest, LambdaQueryWrapper<MessageCenterAll> queryWrapper);

        IPage pageNew(IPage<MessageCenterAll> page, PageRequest pageRequest, LambdaQueryWrapper<MessageCenterAll> queryWrapper);
        }
