package com.liko.crm.workbench.service;

import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.workbench.dao.ActivityDao;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:18
 */
public class ActivityServiceImpl implements ActivityService{
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

}
