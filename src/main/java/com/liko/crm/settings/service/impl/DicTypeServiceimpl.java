package com.liko.crm.settings.service.impl;

import com.liko.crm.settings.dao.DicTypeDao;
import com.liko.crm.settings.service.DicTypeService;
import com.liko.crm.utils.SqlSessionUtil;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:48
 */
public class DicTypeServiceimpl implements DicTypeService{
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
}
