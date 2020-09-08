package com.liko.crm.workbench.service;

import com.liko.crm.settings.dao.UserDao;
import com.liko.crm.settings.domain.User;
import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.dao.ActivityDao;
import com.liko.crm.workbench.dao.ActivityRemarkDao;
import com.liko.crm.workbench.domain.Activity;
import com.liko.crm.workbench.domain.ActivityRemark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:18
 */
public class ActivityServiceImpl implements ActivityService{
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao arDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao =SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public List<ActivityRemark> getRemarkByAid(String aid) {
        List<ActivityRemark> ar=arDao.getRemarkByAid(aid);
        return ar;
    }

    @Override
    public Activity detail(String id) {
        Activity a = activityDao.detail(id);
        return a;
    }

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

    @Override
    public boolean delect(String[] ids) {
        boolean flag =true;
        //因为一对多的关系 要先删除多的表
        //查询市场活动备注表应当删除数量
        int count1=arDao.countByIds(ids);
        //返回市场活动备注表实际删除数量
        int count2 =arDao.deleteByIds(ids);
        //如果不相等删除失败
        if (count1!=count2){
            flag =false;
        }

        int count3 =activityDao.delect(ids);
        //如果不相等删除失败
        if (count3!=ids.length){
            flag =false;
        }
        return flag;
    }

    @Override
    public boolean update(Activity a) {
        boolean flag =true;
        int count =activityDao.update(a);
        if (count!=1){
            flag =false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> selectUlistAndActivity(String id) {
        Map<String,Object> map =new HashMap<>();

        //查询uList
        List<User> uList=userDao.getUserList();
        //查询单条Activity
        Activity a =activityDao.getActivityById(id);
        map.put("uList",uList);
        map.put("activity",a);
        return map;
    }
}
