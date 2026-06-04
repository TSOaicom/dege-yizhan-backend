package com.kuafu.web.mapper;

import java.util.List;
import com.kuafu.web.entity.CarTypeEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p> 车辆类型 Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface CarTypeEnumMapper extends BaseMapper<CarTypeEnum> {

}
