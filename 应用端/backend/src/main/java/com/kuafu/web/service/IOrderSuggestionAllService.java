package com.kuafu.web.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.web.entity.OrderSuggestionAll;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>  订单建议 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IOrderSuggestionAllService extends IService<OrderSuggestionAll> {
        List<OrderSuggestionAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<OrderSuggestionAll> queryWrapper);
        List<OrderSuggestionAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<OrderSuggestionAll> queryWrapper,boolean isPage);

        long selectCount(PageRequest pageRequest, LambdaQueryWrapper<OrderSuggestionAll> queryWrapper);

        IPage pageNew(IPage<OrderSuggestionAll> page, PageRequest pageRequest, LambdaQueryWrapper<OrderSuggestionAll> queryWrapper);
        }
