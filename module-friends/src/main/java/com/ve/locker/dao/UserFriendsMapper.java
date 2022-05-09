package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.UserFriends;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.FriendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiyi
 * @since 2022-01-14
 */
public interface UserFriendsMapper extends BaseMapper<UserFriends> {

    /**
     * 根据用户ID获取用户好友列表
     * @param
     * @return
     */
    List<FriendVo> getFriendsList(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询用户好友数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Integer countFriends(@Param("condition") ConditionVO condition);

    List<FriendVo> searchUsersList(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);
}
