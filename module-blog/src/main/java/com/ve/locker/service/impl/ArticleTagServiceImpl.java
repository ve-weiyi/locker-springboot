package com.ve.locker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ve.locker.dao.ArticleTagDao;
import com.ve.locker.entity.ArticleTag;
import com.ve.locker.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签服务
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagDao, ArticleTag> implements ArticleTagService {

}
