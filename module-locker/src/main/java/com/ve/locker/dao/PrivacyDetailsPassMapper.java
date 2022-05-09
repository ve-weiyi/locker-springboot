package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.PrivacyDetailsPass;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
public interface PrivacyDetailsPassMapper extends BaseMapper<PrivacyDetailsPass> {
    /**
     * 获取隐私最大id
     * @return
     */
    Integer getMaxPrivacyInfoId();
}
