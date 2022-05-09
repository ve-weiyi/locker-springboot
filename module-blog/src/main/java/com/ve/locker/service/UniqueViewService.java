package com.ve.locker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ve.locker.dto.UniqueViewDTO;
import com.ve.locker.entity.UniqueView;

import java.util.List;

/**
 * 用户量统计
 *
 * @author xiaojie
 * @date 2021/07/29
 */
public interface UniqueViewService extends IService<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();

}
