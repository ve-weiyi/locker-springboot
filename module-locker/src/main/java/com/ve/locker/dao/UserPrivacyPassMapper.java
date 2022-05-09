package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.UserPrivacyPass;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.UserPrivacyPassInfoVO;
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
public interface UserPrivacyPassMapper extends BaseMapper<UserPrivacyPass> {
    List<UserPrivacyPassInfoVO> listUserPrivacyInfo(@Param("condition") ConditionVO condition);
}
