package com.kuafu.web.mapper;

import java.util.List;
import com.kuafu.web.entity.InviteRecordAll;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p> 邀请记录 Mapper </p>
 *
 * @author kuafuai
 * @description
 * @date 2026/05/26 11:35
 */
@Mapper
public interface InviteRecordAllMapper extends BaseMapper<InviteRecordAll> {
    public List<InviteRecordAll> selectListNew(@Param("ew") Wrapper<InviteRecordAll> queryWrapper);
}
