package com.kuafu.web.mapper;

import java.util.List;
import com.kuafu.web.entity.OrderSuggestionAll;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p> 订单建议 Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface OrderSuggestionAllMapper extends BaseMapper<OrderSuggestionAll> {
    public List<OrderSuggestionAll> selectListNew(@Param("ew") Wrapper<OrderSuggestionAll> queryWrapper);
}
