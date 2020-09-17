package com.liko.crm.workbench.service;

import com.liko.crm.settings.domain.User;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.domain.Activity;
import com.liko.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:18
 */
public interface ActivityService {

    boolean save(Activity a);


    Pagination<Activity> pageList(Map<String, Object> map);

    boolean delect(String[] ids);

    Map<String, Object> selectUlistAndActivity(String id);

    boolean update(Activity a);

    Activity detail(String id);

    List<ActivityRemark> getRemarkByAid(String aid);

    boolean delRemark(String id);

    boolean saveRemark(ActivityRemark ar);

    boolean UpdateRemark(ActivityRemark ar);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityListByAIdandNotByClueId(Map<String, String> map);
}
