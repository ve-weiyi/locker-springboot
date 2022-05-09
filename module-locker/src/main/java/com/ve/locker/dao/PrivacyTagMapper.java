package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.PrivacyTag;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.PrivacyTagVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
public interface PrivacyTagMapper extends BaseMapper<PrivacyTag> {

    List<PrivacyTagVO> listUserPrivacyTag(@Param("condition") ConditionVO condition);

}
