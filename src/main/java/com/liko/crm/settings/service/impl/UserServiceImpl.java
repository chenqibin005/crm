package com.liko.crm.settings.service.impl;

import com.liko.crm.excepiton.LoginExcepiton;
import com.liko.crm.settings.dao.UserDao;
import com.liko.crm.settings.domain.User;
import com.liko.crm.settings.service.UserService;
import com.liko.crm.utils.DateTimeUtil;
import com.liko.crm.utils.Mybatis;
import com.liko.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hangzhi1063
 * @date 2020/8/31 11:25
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginExcepiton {

        Map<String,String > map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user =userDao.login(map);

        //验证用户是否存在
        if (user==null){
            throw new LoginExcepiton("用户不存在");
        }
        //验证是否在有效期
        String currtTime = DateTimeUtil.getSysTime();
        if (currtTime.compareTo(user.getExpireTime())>0){
            throw new LoginExcepiton("用户登录时间失效");
        }
        //验证是否被封锁
        if ("0".equals(user.getLockState())){
            throw new LoginExcepiton("用户账号被锁定请联系管理员");
        }
        //验证ip
        String ips ="192.168.1.1,192.168.1.2,127.0.0.1"; //ip群
        if (!ips.contains(ip)){
            throw new LoginExcepiton("ip异常登陆失败");
        }


        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> list=userDao.getUserList();
        return list;
    }
}
