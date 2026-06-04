package com.kuafu.web.mapper;

import java.util.List;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuafu.web.entity.MemberTypeEnum;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
/**
 * <p> member_type_enum Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface MemberTypeEnumStatisticsMapper extends BaseMapper<MemberTypeEnum> {

                        List<Object> member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> member_type_enum_member_type_enum_id_status_statistic_691c8685_count(@Param("ew") Wrapper queryWrapper);


                        List<Object> member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(@Param("ew") Wrapper queryWrapper);

}
