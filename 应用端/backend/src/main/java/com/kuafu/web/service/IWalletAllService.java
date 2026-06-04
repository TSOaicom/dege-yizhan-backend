package com.kuafu.web.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.common.domin.PageRequest;
import com.kuafu.web.entity.WalletAll;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;



/**
 * <p>  钱包 服务类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
public interface IWalletAllService extends IService<WalletAll> {
        List<WalletAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<WalletAll> queryWrapper);
        List<WalletAll> selectListNew(PageRequest pageRequest, LambdaQueryWrapper<WalletAll> queryWrapper,boolean isPage);

        long selectCount(PageRequest pageRequest, LambdaQueryWrapper<WalletAll> queryWrapper);

        IPage pageNew(IPage<WalletAll> page, PageRequest pageRequest, LambdaQueryWrapper<WalletAll> queryWrapper);
        }
