package com.ve.locker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ve.locker.dao.UserAuthDao;
import com.ve.locker.dao.UserFriendsMapper;
import com.ve.locker.entity.Relation;
import com.ve.locker.entity.UserFriends;
import com.ve.locker.service.IRelationService;
import com.ve.locker.service.IUserFriendsService;
import com.ve.locker.util.LogUtil;
import com.ve.locker.util.PageUtils;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.FriendVo;
import com.ve.locker.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 * @author weiyi
 * @since 2022-01-14
 */
@Service
public class UserFriendsServiceImpl extends ServiceImpl<UserFriendsMapper, UserFriends> implements IUserFriendsService {

    @Autowired
    UserFriendsMapper userFriendsMapper;
    @Autowired
    UserAuthDao userAuthDao;
    @Autowired
    IRelationService relationsService;

    @Override
    public PageResult<FriendVo> getFriendslist(ConditionVO condition) {
        // 获取用户好友数量
        Integer count = userFriendsMapper.countFriends(condition);
//        Integer count = 10;
        if (count == 0) {
            return new PageResult<>();
        }
        LogUtil.println("-------------------" + count);
        List<FriendVo> friendsList = userFriendsMapper.getFriendsList(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(friendsList, count);
    }

    /**
     * 根据用户ID获取用户好友列表
     * @return
     */
    @Override
    public List<Relation> getRelationslist() {
        return relationsService.list();
    }

    /**
     * 搜索用户
     * @param condition@return
     */
    @Override
    public PageResult<FriendVo> searchUsersList(ConditionVO condition) {
        // 获取用户好友数量
        Integer count = userAuthDao.countUser(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        List<FriendVo> friendsList = userFriendsMapper.searchUsersList(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(friendsList, count);
    }

}
