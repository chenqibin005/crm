package com.liko.crm.workbench.dao;

import com.liko.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/9/3 12:49
 */
public interface ActivityRemarkDao {
    int countByIds(String[] ids);

    int deleteByIds(String[] ids);

    List<ActivityRemark> getRemarkByAid(String aid);

    int delRemark(String id);

    int saveRemark(ActivityRemark ar);

    int UpdateRemark(ActivityRemark ar);
}
