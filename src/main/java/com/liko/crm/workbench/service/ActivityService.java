package com.liko.crm.workbench.service;

import com.liko.crm.settings.domain.User;
import com.liko.crm.vo.Pagination;
import com.liko.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/1 15:18
 */
public interface ActivityService {

    boolean save(Activity a);


    Pagination<Activity> pageList(Map<String, Object> map);
}
