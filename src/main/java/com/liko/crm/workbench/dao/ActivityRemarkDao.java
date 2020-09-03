package com.liko.crm.workbench.dao;

/**
 * @author hangzhi1063
 * @date 2020/9/3 12:49
 */
public interface ActivityRemarkDao {
    int countByIds(String[] ids);

    int deleteByIds(String[] ids);
}
