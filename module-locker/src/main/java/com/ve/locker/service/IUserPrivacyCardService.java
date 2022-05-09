package com.ve.locker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ve.locker.entity.UserPrivacyCard;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.PassingPrivacyInfo;
import com.ve.locker.vo.UserPrivacyCardInfoVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiyi
 * @since 2022-04-10
 */
public interface IUserPrivacyCardService extends IService<UserPrivacyCard> {
    /**
     * 增 添加隐私信息
     *
     * @param userPrivacyInfoVO 数据
     */
    void addPrivacyInfo(UserPrivacyCardInfoVO userPrivacyInfoVO);


    /**
     * 删 删除隐私信息
     *
     *  @param privacyInfoId 数据
     */
    void deletePrivacyInfo(Integer privacyInfoId);

    /**
     * 改 更新隐私信息
     *
     * @param userPrivacyInfoVO 数据
     */
    void updatePrivacyInfo(UserPrivacyCardInfoVO userPrivacyInfoVO);

    /**
     * 查 查询隐私信息
     *
     * @param conditionVO 数据
     */
    List<UserPrivacyCardInfoVO> listUserPrivacyInfo(ConditionVO conditionVO);

    /**
     * 解析隐私信息
     *
     * @param
     */
    Map parsingPrivacyInfo(PassingPrivacyInfo passingPrivacyInfo);
}
