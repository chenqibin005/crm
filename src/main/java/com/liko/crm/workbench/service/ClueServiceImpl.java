package com.liko.crm.workbench.service;

import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.workbench.dao.ClueDao;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:39
 */
public  class ClueServiceImpl implements ClueService{
   private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
}
