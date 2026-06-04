package com.kuafu.web.mapper;

import java.util.List;
import com.kuafu.web.entity.WalletAll;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p> 钱包 Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface WalletAllMapper extends BaseMapper<WalletAll> {
    public List<WalletAll> selectListNew(@Param("ew") Wrapper<WalletAll> queryWrapper);
}
