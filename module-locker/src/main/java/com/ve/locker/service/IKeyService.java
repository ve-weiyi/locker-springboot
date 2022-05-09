package com.ve.locker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ve.locker.entity.Key;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
public interface IKeyService extends IService<Key> {
    /**
     * 根据用户id查询私钥
     * @param userId
     * @return
     */
    String getPrivateKey(Integer userId);

    /**
     * 根据用户id查询公钥
     * @param userId
     * @return
     */
    String getPublicKey(Integer userId);
}
