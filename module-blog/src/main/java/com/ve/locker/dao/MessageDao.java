package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.Message;
import org.springframework.stereotype.Repository;


/**
 * 留言
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Repository
public interface MessageDao extends BaseMapper<Message> {

}
