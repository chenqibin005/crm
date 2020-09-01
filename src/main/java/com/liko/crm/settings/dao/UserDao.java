package com.liko.crm.settings.dao;

import com.liko.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/8/31 11:14
 */
public interface UserDao {
    User login(Map<String, String> map);

    List<User> getUserList();
}
