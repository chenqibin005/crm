package com.liko.crm.settings.service;

import com.liko.crm.excepiton.LoginExcepiton;
import com.liko.crm.settings.domain.User;

/**
 * @author hangzhi1063
 * @date 2020/8/31 11:24
 */
public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginExcepiton;
}
