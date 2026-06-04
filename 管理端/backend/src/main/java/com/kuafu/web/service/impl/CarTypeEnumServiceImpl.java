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

import com.kuafu.web.mapper.CarTypeEnumMapper;
import com.kuafu.web.entity.CarTypeEnum;
import com.kuafu.web.service.ICarTypeEnumService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 车辆类型 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("CarTypeEnum")
public class CarTypeEnumServiceImpl extends ServiceImpl<CarTypeEnumMapper, CarTypeEnum> implements ICarTypeEnumService {


}
