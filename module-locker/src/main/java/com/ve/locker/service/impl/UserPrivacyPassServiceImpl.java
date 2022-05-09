package com.ve.locker.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ve.locker.dao.PrivacyDetailsPassMapper;
import com.ve.locker.dao.PrivacyTagMapper;
import com.ve.locker.dao.UserPrivacyPassMapper;
import com.ve.locker.entity.PrivacyDetailsPass;
import com.ve.locker.entity.UserPrivacyPass;
import com.ve.locker.exception.BizException;
import com.ve.locker.service.IUserPrivacyPassService;
import com.ve.locker.util.BeanCopyUtils;
import com.ve.locker.util.LogUtil;
import com.ve.locker.util.RSAEncryptor;
import com.ve.locker.util.UserUtils;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.PassingPrivacyInfo;
import com.ve.locker.vo.PrivacyTagVO;
import com.ve.locker.vo.UserPrivacyPassInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 * @author weiyi
 * @since 2022-04-10
 */
@Service
public class UserPrivacyPassServiceImpl extends ServiceImpl<UserPrivacyPassMapper, UserPrivacyPass> implements IUserPrivacyPassService {
    @Autowired
    PrivacyDetailsPassMapper privacyDetailsMapper;

    @Autowired
    UserPrivacyPassMapper userPrivacyMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPrivacyInfo(UserPrivacyPassInfoVO userPrivacyInfoVO) {
        LogUtil.println(userPrivacyInfoVO);

        //插入隐私信息
        PrivacyDetailsPass privacyDetails = userPrivacyInfoVO.getPrivacyDetails();
        privacyDetails.setId(privacyDetailsMapper.getMaxPrivacyInfoId() + 1);
        privacyDetailsMapper.insert(privacyDetails);


        //插入用户-隐私 关联
        UserPrivacyPass userPrivacy = BeanCopyUtils.copyObject(userPrivacyInfoVO, UserPrivacyPass.class);
        //userid、privacyid 不能修改
        userPrivacy.setUserId(UserUtils.getUserId())
                .setPrivacyDetailsId(privacyDetails.getId())
                .setUpdateTime(LocalDateTime.now());
        userPrivacyMapper.insert(userPrivacy);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePrivacyInfo(UserPrivacyPassInfoVO userPrivacyInfoVO) {
        LogUtil.println(userPrivacyInfoVO);

        int userId = UserUtils.getUserId();
        int privacyDetailsId = userPrivacyInfoVO.getPrivacyDetails().getId();

        UserPrivacyPass userPrivacy = userPrivacyMapper.selectOne(
                new QueryWrapper<UserPrivacyPass>()
                        .eq("user_id", userId)
                        .eq("privacy_info_id", privacyDetailsId)
        );

        if (userPrivacy == null) {
            throw new BizException(String.format("操作失败！这条隐私不存在您的隐私列表。\n" +
                    "用户id:%d  隐私id:%d", userId, privacyDetailsId));
        }
        //更新 用户-隐私 关联
        UserPrivacyPass newUserPrivacy = BeanCopyUtils.copyObject(userPrivacyInfoVO, UserPrivacyPass.class);
        userPrivacyMapper.updateById(newUserPrivacy);

        //更新 隐私表
        PrivacyDetailsPass newPrivacyInfo = userPrivacyInfoVO.getPrivacyDetails();
        privacyDetailsMapper.updateById(newPrivacyInfo);

        LogUtil.println(newUserPrivacy);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePrivacyInfo(Integer privacyDetailsId) {
        Integer userId = UserUtils.getUserId();

        if (userId == null || privacyDetailsId == null) {
            throw new BizException(String.format("操作失败！隐私信息id:%d", privacyDetailsId));
        }

        UserPrivacyPass userPrivacy = userPrivacyMapper.selectOne(new QueryWrapper<UserPrivacyPass>()
                .eq("privacy_info_id", privacyDetailsId)
                .eq("user_id", userId));

        if (userPrivacy == null) {
            throw new BizException(String.format("操作失败！这条隐私信息已不存在您的隐私列表", privacyDetailsId));
        }
        privacyDetailsMapper.deleteById(privacyDetailsId);
        userPrivacyMapper.deleteById(userPrivacy.getId());
    }

    @Autowired
    PrivacyTagMapper privacyTagMapper;

    @Override
    public List<UserPrivacyPassInfoVO> listUserPrivacyInfo(ConditionVO condition) {
        int userId = UserUtils.getUserId();
        condition.setUserId(userId);
        List<UserPrivacyPassInfoVO> result = userPrivacyMapper.listUserPrivacyInfo(condition);

        result.forEach(
                userPrivacyPassInfoVO ->
                {
                    List<PrivacyTagVO> privacyTags = privacyTagMapper.listUserPrivacyTag(
                            ConditionVO.builder()
                                    .keywords("pass")
                                    .privacyInfoId(userPrivacyPassInfoVO.getId())
                                    .build()
                    );
                    userPrivacyPassInfoVO.setPrivacyTags(privacyTags);
                }

        );
        return result;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map parsingPrivacyInfo(PassingPrivacyInfo passingPrivacyInfo) {
        if (passingPrivacyInfo.getPrivacyObject() == null) {
            throw new BizException("无法解析 privacyObject ,请确保参数传输正确！");
        }
        String privacyJsonString = JSON.toJSONString(passingPrivacyInfo.getPrivacyObject());
        String keyString = passingPrivacyInfo.getKeyString();
        Integer keyType = passingPrivacyInfo.getKeyType();
        Integer model = passingPrivacyInfo.getModel();

        LogUtil.println(privacyJsonString.toString());
        LogUtil.println(keyString);
        LogUtil.println(keyType.toString());
        LogUtil.println(model.toString());

        Map map = JSON.parseObject(privacyJsonString.toString());

        Map privacyInfoNew = RSAEncryptor.parsingRSAMap(
                map,
                keyString,
                keyType,
                model
        );

        LogUtil.println(privacyInfoNew.toString());
        return privacyInfoNew;
    }
}
