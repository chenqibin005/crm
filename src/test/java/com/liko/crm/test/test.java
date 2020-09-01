package com.liko.crm.test;

import com.liko.crm.settings.domain.User;
import com.liko.crm.utils.MD5Util;
import com.liko.crm.utils.SqlSessionUtil;
import com.liko.crm.workbench.dao.ActivityDao;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author hangzhi1063
 * @date 2020/8/31 11:57
 */
public class test {
    @Test
    public void testUserLogin(){
        //验证用户是否在可用期
       /* String expireTime ="2020-08-19 10-44-44";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH-ss-mm");
        String currtTime=sdf.format(new Date());
        int num =currtTime.compareTo(expireTime);
        System.out.println(num);*/
        //验证用户是否被锁定
        String lockState ="0";
        if ("0".equals(lockState)){
            System.out.println("用户被锁定");
        }

        //验证用户ip地址是否合法
        //设置ip地址群
        String ips ="192.168.1.1,192.168.1.2,192.168.1.3";
        String userIp ="192.168.1.5";
        if (ips.contains(userIp)){
            System.out.println("用户ip合法");
        }else{
            System.out.println("用户ip不合法,请联系管理员");
        }
        System.out.println( MD5Util.getMD5("13586380977CQB"));
    }
    @Test
    public void testGet(){
        ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    }
}
