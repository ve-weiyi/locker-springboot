package com.ve.locker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.ve.locker.entity.UserFriends;
import com.ve.locker.service.IUserFriendsService;
import com.ve.locker.service.UserAuthService;
import com.ve.locker.util.UserUtils;
import com.ve.locker.vo.ConditionVO;
import com.ve.locker.vo.RelationshipVO;
import com.ve.locker.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiyi
 * @since 2022-01-14
 */
@Api(tags = "好友管理")
@ApiSupport(order = 35)
@RestController
@RequestMapping("/user/friends")
public class UserFriendsController {
    @Autowired
    IUserFriendsService userFriendsService;
    @Autowired
    UserAuthService userAuthService;

    @ApiOperation(value = "获取当前用户好友列表")
    @GetMapping("/list")
    public Result<?> getFriendsList(ConditionVO condition) {
        //principal 其实就是 User对象，可以 User user=(User)principal;
        int user_id= UserUtils.getUserId();
        condition.setUserId(user_id);
        return Result.ok(userFriendsService.getFriendslist(condition));
    }

    @ApiOperation(value = "按条件查找用户")
    @GetMapping("/search")
    public Result<?> searchUsersList(ConditionVO condition) {
        //principal 其实就是 User对象，可以 User user=(User)principal;;
        return Result.ok(userFriendsService.searchUsersList(condition));
    }

    @ApiOperation(value = "添加好友")
    @PostMapping("/add")
    public Result<?> addFriend(Principal principal,@RequestParam Integer friend_id) {
        if (null == principal || friend_id==null) {
            return Result.fail("操作失败!"+friend_id);
        }
        //principal 其实就是 User对象，可以 User user=(User)principal;
        int user_id=UserUtils.getUserId();
        List<UserFriends> friends=userFriendsService.list(new QueryWrapper<UserFriends>()
                .eq("user_id",user_id)
                .eq("friend_id",friend_id));

        if(!friends.isEmpty()){
            return Result.fail("您与"+friend_id+"已有好友关系");
        }

        if(userAuthService.getById(friend_id)==null){
            return Result.fail("用户id："+friend_id+"不存在");
        }

        boolean res=userFriendsService.save(new UserFriends().setUserId(user_id).setFriendId(friend_id));

        if(res){
            return Result.ok("添加好友成功！");
        }else{
            return Result.fail("添加好友失败！");
        }
    }

    @ApiOperation(value = "修改好友关系")
    @PostMapping("/update")
    public Result<?> updateFriendRelations(Principal principal,
                                           @RequestBody RelationshipVO relationship) {
        Integer friend_id=relationship.getFriendId();
        List<Integer> relation_ids=relationship.getRelationIds();
        if (null == principal || friend_id==null ||relation_ids==null) {
            return Result.fail("操作失败!"+relationship);
        }

        if(userAuthService.getById(friend_id)==null){
            return Result.fail("用户id："+friend_id+"不存在");
        }

        int user_id=UserUtils.getUserId();
        userFriendsService.remove(new QueryWrapper<UserFriends>()
                        .eq("user_id",user_id)
                        .eq("friend_id",friend_id));

        relation_ids.forEach((relation_id)->{
            userFriendsService.save(new UserFriends()
                    .setUserId(user_id)
                    .setFriendId(friend_id)
                    .setRelationId(relation_id));

        });

        return Result.ok("添加关系成功！");
    }

    @ApiOperation(value = "删除好友")
    @DeleteMapping("/delete")
    public Result<?> deleteFriend(Principal principal,@RequestParam Integer friend_id) {
        if (null == principal || friend_id==null) {
            return Result.fail("操作失败!"+friend_id);
        }

        int user_id=UserUtils.getUserId();
        UserFriends friends=userFriendsService.getOne(new QueryWrapper<UserFriends>()
                .eq("user_id", user_id)
                .eq("friend_id", friend_id));

        if(friends==null){
            return Result.fail("您与"+friend_id+"未有好友关系");
        }

        boolean res=userFriendsService.removeById(friends.getId());
        if(res){
            return Result.ok("删除好友成功！");
        }else{
            return Result.fail("删除好友失败！");
        }
    }

}
