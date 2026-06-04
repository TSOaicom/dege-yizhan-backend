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

import com.kuafu.web.mapper.MemberTypeEnumStatisticsMapper;
import com.kuafu.web.entity.MemberTypeEnum;
import com.kuafu.web.service.IMemberTypeEnumStatisticsService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * <p> member_type_enum 服务实现类 </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Slf4j
@Service("MemberTypeEnumStatistics")
public class MemberTypeEnumStatisticsServiceImpl extends ServiceImpl<MemberTypeEnumStatisticsMapper, MemberTypeEnum> implements IMemberTypeEnumStatisticsService {
    @Resource
    private MemberTypeEnumStatisticsMapper memberTypeEnumStaticMapper;

                        @Override
                        public List<Object> member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(LambdaQueryWrapper queryWrapper) {
                                return memberTypeEnumStaticMapper.member_type_enum_member_type_enum_id_status_statistic_0d9c3cae_count(queryWrapper);
                                }


                        @Override
                        public List<Object> member_type_enum_member_type_enum_id_status_statistic_691c8685_count(LambdaQueryWrapper queryWrapper) {
                                return memberTypeEnumStaticMapper.member_type_enum_member_type_enum_id_status_statistic_691c8685_count(queryWrapper);
                                }


                        @Override
                        public List<Object> member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(LambdaQueryWrapper queryWrapper) {
                                return memberTypeEnumStaticMapper.member_type_enum_member_type_enum_id_status_statistic_4f5a8e3f_count(queryWrapper);
                                }


}
