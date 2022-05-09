package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.ChatRecord;
import org.springframework.stereotype.Repository;

/**
 * 聊天记录
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Repository
public interface ChatRecordDao extends BaseMapper<ChatRecord> {
}
