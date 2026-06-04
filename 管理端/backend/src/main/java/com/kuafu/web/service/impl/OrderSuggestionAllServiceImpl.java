package com.kuafu.web.service.impl;

import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kuafu.web.mapper.OrderSuggestionAllMapper;
import com.kuafu.web.entity.OrderSuggestionAll;
import com.kuafu.web.service.IOrderSuggestionAllService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 订单建议 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("OrderSuggestionAll")
public class OrderSuggestionAllServiceImpl extends ServiceImpl<OrderSuggestionAllMapper, OrderSuggestionAll> implements IOrderSuggestionAllService {


}
