package com.liko.crm.workbench.dao;

import com.liko.crm.settings.domain.User;
import com.liko.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:14
 */
public interface ActivityDao {


    int save(Activity a);


    List<Activity> getActivityByCondition(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);

    int delect(String[] ids);

    Activity getActivityById(String id);

    int update(Activity a);

    Activity detail(String id);
}
