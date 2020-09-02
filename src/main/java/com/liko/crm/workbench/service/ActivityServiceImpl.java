package com.liko.crm.workbench.service;

import com.liko.crm.settings.domain.User;
import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.dao.ActivityDao;
import com.liko.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:18
 */
public class ActivityServiceImpl implements ActivityService{
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity a) {
        boolean flag =true;
        int count =activityDao.save(a);
        if (count!=1){
            flag =false;
        }
        return flag;
    }

    @Override
    public Pagination<Activity> pageList(Map<String, Object> map) {

        //总记录数total
        int total =activityDao.getTotalByCondition(map);
        //Activity list集合
        List<Activity> list =activityDao.getActivityByCondition(map);

        //创建vo对象
        Pagination<Activity> vo =new Pagination<>();
        vo.setTotal(total);
        vo.setList(list);
        return vo;
    }
}
