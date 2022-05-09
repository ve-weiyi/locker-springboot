package com.ve.locker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ve.locker.dao.KeyMapper;
import com.ve.locker.entity.Key;
import com.ve.locker.service.IKeyService;
import com.ve.locker.util.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
@Service
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements IKeyService {
    @Autowired
    KeyMapper keyMapper;

    /**
     * 根据用户id查询私钥
     * @param userId
     * @return
     */
    @Override
    public String getPrivateKey(Integer userId) {
        Key key= keyMapper.selectOne(new QueryWrapper<Key>()
                //用户id匹配
                .eq("id",userId ));
        if(key!=null) {
            return key.getPrivateKey();
        } else {
            //创建秘钥
            Map<String, String> keyStrMap = RSAUtils.generateRSAKeyStrMap();
            String publicKeyStr=keyStrMap.get("publicKeyStr");
            String privateKeyStr=keyStrMap.get("privateKeyStr");
            Key userKey=new Key();
            userKey.setId(userId)
                    .setPublicKey(publicKeyStr)
                    .setPrivateKey(privateKeyStr);
            keyMapper.insert(userKey);
            return privateKeyStr;
        }
    }

    @Autowired
    IKeyService keyService;
    /**
     * 根据用户id查询公钥
     * @param userId
     * @return
     */
    @Override
    public String getPublicKey(Integer userId) {
        Key key= keyMapper.selectOne(new QueryWrapper<Key>()
                //用户id匹配
                .eq("id",userId ));
        if(key!=null) {
            return key.getPublicKey();
        } else {
            //创建秘钥
            Map<String, String> keyStrMap = RSAUtils.generateRSAKeyStrMap();
            String publicKeyStr=keyStrMap.get("publicKeyStr");
            String privateKeyStr=keyStrMap.get("privateKeyStr");
            Key userKey=new Key();
            userKey.setId(userId)
                    .setPublicKey(publicKeyStr)
                    .setPrivateKey(privateKeyStr);
            keyMapper.insert(userKey);

            return publicKeyStr;
        }
    }
}
