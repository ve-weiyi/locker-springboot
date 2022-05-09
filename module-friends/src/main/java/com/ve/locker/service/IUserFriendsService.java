package com.ve.locker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ve.locker.entity.Relation;
import com.ve.locker.entity.UserFriends;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.FriendVo;
import com.ve.locker.vo.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiyi
 * @since 2022-01-14
 */
public interface IUserFriendsService extends IService<UserFriends> {

    /**
     * 根据用户ID获取用户好友列表
     * @param
     * @return
     */
    PageResult<FriendVo> getFriendslist(ConditionVO condition);

    /**
     * 获取关系列表
     * @param
     * @return
     */
    List<Relation> getRelationslist();

    /**
     * 搜索用户
     * @param
     * @return
     */
    PageResult<FriendVo> searchUsersList(ConditionVO condition);
}
